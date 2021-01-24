package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.Month;

public class ResumeTestData {
    public static void main(String[] args) {
    }

    public static Resume getInstanceResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        resume.addSection(SectionType.EXPERIENCE,
                new OrganizationListSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Experience(2005, Month.JANUARY, "position1", "content1"),
                                new Experience(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        resume.addSection(SectionType.EDUCATION,
                new OrganizationListSection(
                        new Organization("Institute", "url",
                                new Experience(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru",
                                new Experience(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null))));
        resume.addContact(ContactType.PHONE, "+1 - 666");
        resume.addContact(ContactType.EMAIL, "kit@mail.ru");
        resume.addContact(ContactType.GITHUB, "github");
        resume.addContact(ContactType.SKYPE, "skype2");
        return resume;
    }
}
