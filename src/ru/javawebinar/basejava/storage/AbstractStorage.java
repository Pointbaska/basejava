package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object key = getKeyIfResumeExist(uuid);
        updateResume(resume, key);
        System.out.println("Resume " + uuid + " updated");
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Object key = getKeyIfResumeNotExist(uuid);
        addResume(resume, key);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getKeyIfResumeExist(uuid);
        return getResume(key);
    }

    @Override
    public void delete(String uuid) {
        Object key = getKeyIfResumeExist(uuid);
        deleteResume(key);
    }

    public Object getKeyIfResumeNotExist(String uuid) {
        Object key = getKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    public Object getKeyIfResumeExist(String uuid) {
        Object key = getKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    protected abstract void addResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract void deleteResume(Object key);

    protected abstract Resume getResume(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract boolean isExist(Object uuid);
}
