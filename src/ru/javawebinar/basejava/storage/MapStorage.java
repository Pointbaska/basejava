package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new LinkedHashMap<>();

    @Override
    protected void addResume(Resume resume, Object key) {
        mapStorage.put((String) key, resume);
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        mapStorage.put((String) key, resume);
    }

    @Override
    protected void deleteResume(Object key) {
        mapStorage.remove(key);
    }

    @Override
    protected Resume getResume(Object key) {
        return mapStorage.get(key);
    }

    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String uuid) {
        return mapStorage.containsKey(uuid);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return mapStorage.values().toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
