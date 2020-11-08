package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class ListStorage extends AbstractStorage {

    protected ArrayList<Resume> storage = new ArrayList();

    @Override
    protected boolean isResumeContains(Resume resume) {
        return storage.contains(resume);
    }

    @Override
    public void addResume(Resume resume) {
        storage.add(resume);
    }

    @Override
    public void updateResume(Resume resume) {
        int index = storage.indexOf(resume);
        storage.set(index, new Resume(resume.getUuid()));
        System.out.println("Resume " + resume.getUuid() + " updated");
    }

    @Override
    public void deleteResume(String uuid) {
        Iterator<Resume> iterator = storage.iterator();
        while (iterator.hasNext()) {
            String actualUuid = iterator.next().getUuid();
            if (Objects.equals(uuid, actualUuid)) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public Resume getResume(String uuid) {
        for (Resume resume : storage) {
            if (Objects.equals(uuid, resume.getUuid())) {
                return resume;
            }
        }
        return null;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = getResume(uuid);
        if (resume == null) {
            return -1;
        }
        return storage.indexOf(resume);
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
        Resume[] resumes = new Resume[storage.size()];
        for (int i = 0; i < storage.size(); i++) {
            resumes[i] = storage.get(i);
        }
        return resumes;
    }
}
