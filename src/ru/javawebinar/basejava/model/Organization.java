package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public Link getLink() {
        return homePage;
    }

    public void addExperience(Experience experience) {
        experienceList.add(experience);
    }

    public void removeExperience(Experience experience) {
        experienceList.remove(experience);
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
}
