package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {

    private Map<String, Resume> mapStorage = new LinkedHashMap<>();

    @Override
    protected void addResume(Resume resume, String searchKey) {
        mapStorage.put(searchKey, resume);
    }

    @Override
    protected void updateResume(Resume resume, String searchKey) {
        mapStorage.put(searchKey, resume);
    }

    @Override
    protected void deleteResume(String searchKey) {
        mapStorage.remove(searchKey);
    }

    @Override
    protected Resume getResume(String searchKey) {
        return mapStorage.get(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return mapStorage.containsKey(searchKey);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
