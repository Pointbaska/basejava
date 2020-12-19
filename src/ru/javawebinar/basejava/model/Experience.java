package ru.javawebinar.basejava.model;

public class Experience {
    private String place;
    private String period;
    private String experience;

    public Experience(String place, String period, String experience) {
        this.place = place;
        this.period = period;
        this.experience = experience;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return place + "\n" + period + " " + experience;
    }
}
