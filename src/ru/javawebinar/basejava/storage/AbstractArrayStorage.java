package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected void addResume(Resume resume, Integer index) {
        if (size < STORAGE_LIMIT) {
            saveResume(resume, index);
            size++;
        } else {
            throw new StorageException("Resume overflowing", resume.getUuid());
        }
    }

    @Override
    protected void updateResume(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    protected void deleteResume(Integer index) {
        fillStorage(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    protected boolean isExist(Integer index) {
        return index > -1;
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
    public List<Resume> getAll() {
        Resume[] arrayResume = Arrays.copyOf(storage, size);
        return Arrays.asList(arrayResume);
    }

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void fillStorage(int index);

    protected abstract Integer getSearchKey(String uuid);
}
