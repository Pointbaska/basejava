package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        checkNotExist(uuid);
        Object key = getKey(uuid);
        updateResume(resume, key);
        System.out.println("Resume " + uuid + " updated");
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        checkExist(uuid);
        Object key = getKey(uuid);
        addResume(resume, key);
    }

    @Override
    public Resume get(String uuid) {
        checkNotExist(uuid);
        Object key = getKey(uuid);
        return getResume(key);
    }

    @Override
    public void delete(String uuid) {
        checkNotExist(uuid);
        Object key = getKey(uuid);
        deleteResume(key);
    }

    public void checkExist(String uuid) {
        if (isExist(uuid)) {
            throw new ExistStorageException(uuid);
        }
    }

    public void checkNotExist(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void addResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract void deleteResume(Object key);

    protected abstract Resume getResume(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract boolean isExist(String uuid);
}
