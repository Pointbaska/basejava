package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private List<Experience> experienceList;

    public Organization(String name, String url) {
        this.homePage = new Link(name, url);
        experienceList = new ArrayList<>();
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

    public List<Experience> getListExperience() {
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
