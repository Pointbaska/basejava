package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    public static final ListSection EMPTY = new ListSection("");

    private List<String> listText = new ArrayList<>();

    public ListSection() {
    }

    public ListSection(String... listText) {
        this(Arrays.asList(listText));
    }

    public ListSection(List<String> listText) {
        this.listText = listText;
    }

    public void addText(String text) {
        listText.add(text);
    }

    public void removeText(String text) {
        listText.remove(text);
    }

    public List<String> getListText() {
        return listText;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String s : listText) {
            result.append(s).append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return Objects.equals(listText, that.listText);
    }

    @Override
    public int hashCode() {
        return listText != null ? listText.hashCode() : 0;
    }
}

