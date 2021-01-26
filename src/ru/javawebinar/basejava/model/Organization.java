package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;
    private List<Experience> experienceList;

    public Organization() {
    }

    public Organization(String name, String url, Experience... experienceList) {
        this(new Link(name, url), Arrays.asList(experienceList));
    }

    public Organization(Link homePage, List<Experience> experienceList) {
        this.homePage = homePage;
        this.experienceList = experienceList;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", experienceList=" + experienceList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!Objects.equals(homePage, that.homePage)) return false;
        return Objects.equals(experienceList, that.experienceList);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + (experienceList != null ? experienceList.hashCode() : 0);
        return result;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Experience implements Serializable {
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public Experience() {
        }

        public Experience(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Experience(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Experience(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Experience Experience = (Experience) o;
            return Objects.equals(startDate, Experience.startDate) &&
                    Objects.equals(endDate, Experience.endDate) &&
                    Objects.equals(title, Experience.title) &&
                    Objects.equals(description, Experience.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }

        @Override
        public String toString() {
            return "Experience(" + startDate + ',' + endDate + ',' + title + ',' + description + ')';
        }
    }
}
