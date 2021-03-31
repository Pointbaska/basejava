package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.AbstractSection;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;
import ru.javawebinar.basejava.sql.SqlHelper;
import ru.javawebinar.basejava.util.JsonParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            String uuid = resume.getUuid();
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name=? WHERE uuid=?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(uuid);
                }
            }
            deleteContacts(conn, resume);
            deleteSections(conn, resume);
            insertContact(conn, resume);
            insertSection(conn, resume);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("" +
                            " INSERT INTO resume " +
                            " (uuid, full_name) VALUES (?, ?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    insertContact(conn, resume);
                    insertSection(conn, resume);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(resume, rs);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section WHERE resume_uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(resume, rs);
                }
            }

            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.<Void>execute("DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> map = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "SELECT * FROM resume r " +
                    "ORDER BY full_name, uuid ")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    String fullName = rs.getString("full_name");
                    Resume resume = new Resume(uuid, fullName);
                    map.put(uuid, resume);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("resume_uuid");
                    addContact(map.get(uuid), rs);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("resume_uuid");
                    addSection(map.get(uuid), rs);
                }
            }

            return new ArrayList<>(map.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", ps -> {
                    ResultSet rs = ps.executeQuery();
                    return rs.next() ? rs.getInt(1) : 0;
                }
        );
    }

    private void insertContact(Connection conn, Resume resume) throws SQLException {
        Map<ContactType, String> contacts = resume.getContacts();
        if (!contacts.isEmpty()) {
            try (PreparedStatement ps = conn.prepareStatement("" +
                    " INSERT INTO contact " +
                    " (resume_uuid, type, value) VALUES (?,?,?)")) {
                for (Map.Entry<ContactType, String> e : contacts.entrySet()) {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, e.getKey().name());
                    ps.setString(3, e.getValue());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    private void insertSection(Connection conn, Resume resume) throws SQLException {
        Map<SectionType, AbstractSection> sections = resume.getSections();
        if (!sections.isEmpty()) {
            try (PreparedStatement ps = conn.prepareStatement("" +
                    " INSERT INTO section " +
                    " (resume_uuid, type, content) VALUES (?,?,?)")) {
                for (Map.Entry<SectionType, AbstractSection> e : sections.entrySet()) {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, e.getKey().name());
                    AbstractSection section = e.getValue();
                    ps.setString(3, JsonParser.write(section, AbstractSection.class));
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    private void deleteContacts(Connection conn, Resume resume) throws SQLException {
        deleteAttributes(conn, resume, "DELETE FROM contact WHERE resume_uuid=?");
    }

    private void deleteSections(Connection conn, Resume resume) throws SQLException {
        deleteAttributes(conn, resume, "DELETE FROM section WHERE resume_uuid=?");
    }

    private void deleteAttributes(Connection conn, Resume resume, String sql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void addContact(Resume resume, ResultSet rs) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void addSection(Resume resume, ResultSet rs) throws SQLException {
        String content = rs.getString("content");
        if (content != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            resume.addSection(type, JsonParser.read(content, AbstractSection.class));
        }
    }
}
