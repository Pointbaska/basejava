package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.Month;
import java.util.EnumMap;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
    }

    public Resume getInstanseResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        TextSection personal = new TextSection();
        personal.setText("Аналитический склад ума, сильная логика, креативность," +
                " инициативность. Пурист кода и архитектуры.");

        TextSection objective = new TextSection();
        objective.setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

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

        Organization wrike = new Organization("Wrike", "wrike.com");
        Experience wrikeExperience = new Experience(DateUtil.of(2014, Month.of(10)),
                DateUtil.of(2016, Month.of(1)), "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven " +
                "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        wrike.addExperience(wrikeExperience);

        Organization enkata = new Organization("Enkate", "enkate.com");
        Experience enkataExperience = new Experience(DateUtil.of(2007, Month.of(3)),
                DateUtil.of(2008, Month.of(6)), "Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей " +
                "кластерного J2EE приложения.");
        enkata.addExperience(enkataExperience);

        OrganizationListSection workOrganizations = new OrganizationListSection();
        workOrganizations.addOrganization(wrike);
        workOrganizations.addOrganization(enkata);

        Organization coursera = new Organization("Coursera", "coursera.com");
        Experience courseraExperience = new Experience(DateUtil.of(2013, Month.of(3)), DateUtil.of(2013, Month.of(5)),
                "\"Functional Programming Principles in Scala\" by Martin Odersky");
        coursera.addExperience(courseraExperience);

        Organization luxoft = new Organization("Luxoft", "luxoft.org");
        Experience luxoftExperience = new Experience(DateUtil.of(2011, Month.of(3)), DateUtil.of(2011, Month.of(4)),
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        luxoft.addExperience(luxoftExperience);

        OrganizationListSection educationOrganizations = new OrganizationListSection();
        educationOrganizations.addOrganization(coursera);
        educationOrganizations.addOrganization(luxoft);

        Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
        sections.put(SectionType.PERSONAL, personal);
        sections.put(SectionType.OBJECTIVE, objective);
        sections.put(SectionType.ACHIEVEMENT, achievement);
        sections.put(SectionType.QUALIFICATIONS, qualifications);
        sections.put(SectionType.EXPERIENCE, workOrganizations);
        sections.put(SectionType.EDUCATION, educationOrganizations);

        resume.setSectionType(sections);

        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        contacts.put(ContactType.PHONE, "+7(921)855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/");

        resume.setContacts(contacts);

        return resume;
    }
}
