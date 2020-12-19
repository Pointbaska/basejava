package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {
    private List<String> listText = new ArrayList<>();

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
}

