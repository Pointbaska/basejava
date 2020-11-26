package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void addResume(Resume resume, Object index) {
        if (size < STORAGE_LIMIT) {
            saveResume(resume, (Integer) index);
            size++;
        } else {
            throw new StorageException("Resume overflowing", resume.getUuid());
        }
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected void deleteResume(Object index) {
        fillStorage((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index > -1;
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
    public List<Resume> getAllSorted() {
        Resume[] arrayResume = Arrays.copyOf(storage, size);
        Arrays.sort(arrayResume);
        return Arrays.asList(arrayResume);
    }

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void fillStorage(int index);

    protected abstract Object getSearchKey(String uuid);
}
