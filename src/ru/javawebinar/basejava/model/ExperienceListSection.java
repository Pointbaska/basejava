package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExperienceListSection extends AbstractSection {
    private List<Experience> experienceList;

    public ExperienceListSection() {
        experienceList = new ArrayList<>();
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
        StringBuilder result = new StringBuilder();
        for (Experience experience : experienceList) {
            result.append(experience.toString()).append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExperienceListSection that = (ExperienceListSection) o;

        return Objects.equals(experienceList, that.experienceList);
    }

    @Override
    public int hashCode() {
        return experienceList != null ? experienceList.hashCode() : 0;
    }
}
