package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private Resume[] resume = new Resume[4];

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));

        resume[0] = new Resume(UUID_1);
        resume[1] = new Resume(UUID_2);
        resume[2] = new Resume(UUID_3);
        resume[3] = new Resume(UUID_4);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume(UUID_4));
        Assert.assertArrayEquals(resume, storage.getAll());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void update() throws Exception {
        storage.update(storage.get(UUID_1));
        arrayAssert(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("uuid5"));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
        arrayAssert(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(storage.get(UUID_2), resume[1]);
        arrayAssert(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception {
        arrayAssert(resume);
    }

    @Test(expected = StorageException.class)
    public void getOverflowing() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < 10000; i++) {
                storage.save(new Resume());
            }
        } catch (Exception exception) {
            Assert.fail("Overflow occurred ahead of time");
        }
        storage.save(new Resume());
    }

    public void arrayAssert(Resume resume[]) {
        Resume expectedResume[] = new Resume[storage.size()];
        for (int i = 0; i < expectedResume.length; i++) {
            expectedResume[i] = resume[i];
        }
        Assert.assertArrayEquals(expectedResume, storage.getAll());
    }
}