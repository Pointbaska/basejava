package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void addResume(Resume resume, Object key) {
        if (size < STORAGE_LIMIT) {
            saveResume(resume, (Integer) key);
            size++;
        } else {
            throw new StorageException("Resume overflowing", resume.getUuid());
        }
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage[(Integer) key] = resume;
    }

    @Override
    protected void deleteResume(Object uuid) {
        fillStorage((Integer) uuid);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(Object key) {
        return storage[(Integer) key];
    }

    @Override
    protected boolean isExist(String uuid) {
        if ((Integer) getKey(uuid) > -1) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void fillStorage(int index);

    protected abstract Object getKey(String uuid);
}
