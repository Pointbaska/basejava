package ru.javawebinar.basejava.model;

public enum Contact {
    PHONE("Телефон"),
    SKYPE("Skype"),
    EMAIL("Почта"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private final String title;

    Contact(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
