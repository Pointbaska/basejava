package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object key = checkNotExistAndGetKey(uuid);
        updateResume(resume, key);
        System.out.println("Resume " + uuid + " updated");
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Object key = checkExistAndGetKey(uuid);
        addResume(resume, key);
    }

    @Override
    public Resume get(String uuid) {
        Object key = checkNotExistAndGetKey(uuid);
        return getResume(key);
    }

    @Override
    public void delete(String uuid) {
        Object key = checkNotExistAndGetKey(uuid);
        deleteResume(key);
    }

    public Object checkExistAndGetKey(String uuid) {
        if (isExist(uuid)) {
            throw new ExistStorageException(uuid);
        }
        return getKey(uuid);
    }

    public Object checkNotExistAndGetKey(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return getKey(uuid);
    }

    protected abstract void addResume(Resume resume, Object key);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract void deleteResume(Object key);

    protected abstract Resume getResume(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract boolean isExist(String uuid);
}
