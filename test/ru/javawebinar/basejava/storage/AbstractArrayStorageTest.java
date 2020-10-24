package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final Resume UUID_1 = new Resume("uuid1");
    private static final Resume UUID_2 = new Resume("uuid2");
    private static final Resume UUID_3 = new Resume("uuid3");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(UUID_1);
        storage.save(UUID_2);
        storage.save(UUID_3);
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
        Resume uuid4 = new Resume("uuid4");
        storage.save(uuid4);
        Assert.assertEquals(uuid4, storage.get("uuid4"));
    }

    @Test
    public void update() throws Exception {
        storage.update(UUID_1);
        Assert.assertEquals(UUID_1, storage.get("uuid1"));
    }

    @Test
    public void delete() throws Exception {
        storage.delete("uuid3");
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(UUID_2, storage.get("uuid2"));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(3, resumes.length);
        for (int i = 0; i < resumes.length; i++) {
            Resume r = resumes[i];
            Assert.assertEquals(r, storage.get(r.getUuid()));
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() throws Exception {
        storage.save(UUID_1);
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
}