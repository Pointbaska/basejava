package ru.javawebinar.basejava.model;

public class Experience {
    private String place;
    private String link;
    private String period;
    private String experience;

    public Experience(String place, String link, String period, String experience) {
        this.place = place;
        this.link = link;
        this.period = period;
        this.experience = experience;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
