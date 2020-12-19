package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.HashMap;
import java.util.Map;

public class ResumeTestDat {
    public static void main(String[] args) {

        Resume resume = new Resume("Григорий Кислин");

        TextSection personal = new TextSection();
        personal.setText("Аналитический склад ума, сильная логика, креативность," +
                " инициативность. Пурист кода и архитектуры.");

        TextSection objective = new TextSection();
        objective.setText("Ведущий стажировок и корпоративного обучения по Java Web" +
                " и Enterprise технологиям");

        ListSection achievement = new ListSection();
        achievement.addText("Реализация протоколов по приему платежей всех основных платежных " +
                "системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        achievement.addText("Реализация двухфакторной аутентификации для онлайн платформы " +
                "управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.addText("Реализация c нуля Rich Internet Application приложения на стеке технологий" +
                " JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5");

        ListSection qualifications = new ListSection();
        qualifications.addText("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.addText("MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.addText("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");

        Experience wrike = new Experience("Wrike", "10/2014 - 01/2016", "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis,. " +
                "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Experience enkata = new Experience("Enkata", "03/2007 - 06/2008", "Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) " +
                "и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");

        ExperienceListSection workExperience = new ExperienceListSection();
        workExperience.addExperience(wrike);
        workExperience.addExperience(enkata);

        Experience coursera = new Experience("Coursera", "03/2013 - 05/2013", "\"Functional Programming Principles in Scala\" by Martin Odersky");
        Experience luxoft = new Experience("Luxoft", "03/2011 - 04/2011", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");

        ExperienceListSection educationExperience = new ExperienceListSection();
        educationExperience.addExperience(coursera);
        educationExperience.addExperience(luxoft);

        Map<SectionType, AbstractSection> section = new HashMap<>();
        section.put(SectionType.PERSONAL, personal);
        section.put(SectionType.OBJECTIVE, objective);
        section.put(SectionType.ACHIEVEMENT, achievement);
        section.put(SectionType.QUALIFICATIONS, qualifications);
        section.put(SectionType.EXPERIENCE, workExperience);
        section.put(SectionType.EDUCATION, educationExperience);

        resume.setSectionType(section);
        for (Map.Entry<SectionType, AbstractSection> map : section.entrySet()) {
            System.out.println(map.getKey().getTitle() + "\n" + resume.getSection(map.getKey()));
        }

        Map<Contact, String> contacts = new HashMap<>();
        contacts.put(Contact.PHONE, "+7(921)855-0482");
        contacts.put(Contact.SKYPE, "grigory.kislin");
        contacts.put(Contact.EMAIL, "gkislin@yandex.ru");
        contacts.put(Contact.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(Contact.GITHUB, "https://github.com/gkislin");
        contacts.put(Contact.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        contacts.put(Contact.HOMEPAGE, "http://gkislin.ru/");

        resume.setContacts(contacts);
        for (Map.Entry<Contact, String> map : contacts.entrySet()) {
            System.out.println(map.getKey().getTitle() + ": " + resume.getContact(map.getKey()));
        }
    }
}
