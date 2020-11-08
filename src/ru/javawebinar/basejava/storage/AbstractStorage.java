package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        if (isResumeContains(resume)) {
            updateResume(resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume resume) {
        if (!isResumeContains(resume)) {
            addResume(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(uuid);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(uuid);
        }
    }

    protected abstract boolean isResumeContains(Resume resume);

    protected abstract void addResume(Resume resume);

    protected abstract void updateResume(Resume resume);

    protected abstract void deleteResume(String uuid);

    protected abstract Resume getResume(String uuid);

    protected abstract int getIndex(String uuid);
}
