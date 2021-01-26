package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataSerializeStrategy implements SerializeStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeWithException(sections.entrySet(), dos, pair -> {
                SectionType sectionType = pair.getKey();
                dos.writeUTF(pair.getKey().name());

                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> {
                        dos.writeUTF(((TextSection) pair.getValue()).getText());
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> list = ((ListSection) pair.getValue()).getListText();
                        writeWithException(list, dos, dos::writeUTF);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> orgList = ((OrganizationListSection) pair.getValue()).getOrganizationList();
                        writeWithException(orgList, dos, e ->
                        {
                            dos.writeUTF(e.getHomePage().getName());
                            String url = e.getHomePage().getUrl();
                            dos.writeUTF(url == null ? "null" : url);
                            List<Organization.Experience> expList = e.getExperienceList();
                            writeWithException(expList, dos, l -> {
                                        dos.writeUTF(l.getStartDate().toString());
                                        dos.writeUTF(l.getEndDate().toString());
                                        dos.writeUTF(l.getTitle());
                                        String description = l.getDescription();
                                        dos.writeUTF(description == null ? "null" : description);
                                    }
                            );
                        });
                    }
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readWithException(dis, () ->
                    resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()
                    ));

            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> {
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> listSection = new ArrayList<>();
                        readWithException(dis, () ->
                                listSection.add(dis.readUTF()));
                        resume.addSection(sectionType, new ListSection(listSection));
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> organizations = new ArrayList<>();
                        readWithException(dis, () ->
                                {
                                    String name = dis.readUTF();
                                    String url = dis.readUTF();
                                    List<Organization.Experience> experienceList = new ArrayList<>();
                                    readWithException(dis, () -> {
                                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                                String title = dis.readUTF();
                                                String description = dis.readUTF();
                                                experienceList.add(new Organization.Experience(
                                                        startDate,
                                                        endDate,
                                                        title,
                                                        description.equals("null") ? null : description));
                                            }
                                    );
                                    organizations.add(new Organization(new Link(name, url.equals("null") ? null : url),
                                            experienceList));
                                }
                        );
                        resume.addSection(sectionType, new OrganizationListSection(organizations));
                    }
                }
            });
            return resume;
        }
    }

    private static <T> void writeWithException(Collection<T> collection, DataOutputStream dos,
                                               SerializeConsumer<T> consumer) throws IOException {
        Objects.requireNonNull(consumer);
        dos.writeInt(collection.size());
        for (T t : collection) {
            consumer.write(t);
        }
    }

    private static void readWithException(DataInputStream dis, SerializeReader reader) throws IOException {
        Objects.requireNonNull(reader);
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }
}
