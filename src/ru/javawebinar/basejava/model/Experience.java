package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience {
    private final Link homePage;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String experience;

    public Experience(String name, String url, LocalDate startDate, LocalDate endDate, String experience) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(experience, "experience must not be null");
        this.homePage = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.experience = experience;
    }

    public Link getLink() {
        return homePage;
    }

    public String getExperience() {
        return experience;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience that = (Experience) o;

        if (!homePage.equals(that.homePage)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        return experience.equals(that.experience);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + experience.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "homePage = " + homePage +
                ", startDate = " + startDate +
                ", endDate = " + endDate +
                ", experience = '" + experience + '\'' +
                '}';
    }
}
