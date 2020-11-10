package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList();

    @Override
    public void addResume(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    public void updateResume(Resume resume, int index) {
        storage.set(index, resume);
    }

    @Override
    public void deleteResume(int index) {
        storage.remove(index);
    }

    @Override
    public Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    protected int getIndex(String uuid) {
        int index = -1;
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                index = storage.indexOf(resume);
            }
        }
        return index;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }
}
