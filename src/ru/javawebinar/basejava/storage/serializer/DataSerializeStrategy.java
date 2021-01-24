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
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> pair : sections.entrySet()) {
                SectionType sectionType = pair.getKey();

                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> {
                        dos.writeUTF(pair.getKey().name());
                        dos.writeUTF(((TextSection) pair.getValue()).getText());
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        dos.writeUTF(pair.getKey().name());
                        List<String> list = ((ListSection) pair.getValue()).getListText();
                        writeWithoutException(list, dos, dos::writeUTF);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        dos.writeUTF(pair.getKey().name());
                        List<Organization> orgList = ((OrganizationListSection) pair.getValue()).getOrganizationList();
                        writeWithoutException(orgList, dos, e ->
                        {
                            dos.writeUTF(e.getHomePage().getName());
                            dos.writeUTF(e.getHomePage().getUrl());
                            List<Experience> expList = e.getExperienceList();
                            writeWithoutException(expList, dos, l -> {
                                        dos.writeUTF(l.getStartDate().toString());
                                        dos.writeUTF(l.getEndDate().toString());
                                        dos.writeUTF(l.getTitle());
                                        if (l.getDescription() != null) {
                                            dos.writeUTF(l.getDescription());
                                        } else dos.writeUTF("null");
                                    }
                            );
                        });
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int contactSize = dis.readInt();
            for (int i = 0; i < contactSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> resume.addSection(sectionType, new TextSection(dis.readUTF()));
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> listSection = new ArrayList<>();
                        int listSectionSize = dis.readInt();
                        for (int j = 0; j < listSectionSize; j++) {
                            listSection.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(listSection));
                    }
                    case EXPERIENCE, EDUCATION -> {
                        int organizationsSize = dis.readInt();
                        List<Organization> organizations = new ArrayList<>();
                        for (int j = 0; j < organizationsSize; j++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            List<Experience> experienceList = new ArrayList<>();
                            int experienceListSize = dis.readInt();
                            for (int k = 0; k < experienceListSize; k++) {
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                String title = dis.readUTF();
                                String description = dis.readUTF();
                                experienceList.add(new Experience(
                                        startDate,
                                        endDate,
                                        title,
                                        description.equals("null") ? null : description));
                            }
                            organizations.add(new Organization(new Link(name, url.equals("null") ? null : url), experienceList));
                        }
                        resume.addSection(sectionType, new OrganizationListSection(organizations));
                    }
                }
            }
            return resume;
        }
    }

    private static <T> void writeWithoutException(Collection<T> collection, DataOutputStream dos,
                                                  SerializeConsumer<T> consumer) throws IOException {
        Objects.requireNonNull(consumer);
        dos.writeInt(collection.size());
        for (T t : collection) {
            consumer.write(t);
        }
    }
}
