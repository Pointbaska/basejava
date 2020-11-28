package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new LinkedHashMap<>();

    @Override
    protected void addResume(Resume resume, Object searchKey) {
        mapStorage.put((String) searchKey, resume);
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        mapStorage.put((String) searchKey, resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        mapStorage.remove(searchKey);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return mapStorage.get(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return mapStorage.containsKey(searchKey);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public List<Resume> getAll() {
        List<Resume> list = new ArrayList<>(mapStorage.values());
        return list;
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
