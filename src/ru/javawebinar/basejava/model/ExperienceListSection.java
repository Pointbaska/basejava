package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

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
}
