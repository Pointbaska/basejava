package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractStorageTest {

    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";

    protected static final Resume RESUME_1 = new Resume(UUID_1, "D");
    protected static final Resume RESUME_2 = new Resume(UUID_2, "C");
    protected static final Resume RESUME_3 = new Resume(UUID_3, "B");
    protected static final Resume RESUME_4 = new Resume(UUID_4, "A");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1, "D"));
        storage.save(new Resume(UUID_2, "C"));
        storage.save(new Resume(UUID_3, "B"));
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
        storage.save(new Resume(UUID_4, "A"));
        assertArray(RESUME_4, RESUME_3, RESUME_2, RESUME_1);
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1, "D"));
    }

    @Test
    public void update() throws Exception {
        storage.update(new Resume(UUID_1, "D"));
        assertArray(RESUME_3, RESUME_2, RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("uuid5", "E"));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
        assertArray(RESUME_2, RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception {
        assertArray(RESUME_3, RESUME_2, RESUME_1);
    }

    public void assertArray(Resume... resumes) {
        Assert.assertEquals(Arrays.asList(resumes), storage.getAllSorted());
    }
}