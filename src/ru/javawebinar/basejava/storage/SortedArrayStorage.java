package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertStorage(Resume resume, int index) {
        int insertionPoint = -index - 1;
        if (insertionPoint < size - 1) {
            System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
            storage[insertionPoint] = resume;
        }
    }

    @Override
    protected void fillStorage(int index) {
        int movedNum = size - index - 1;
        if (movedNum > 0) {
            System.arraycopy(storage, index + 1, storage, index, movedNum);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
