package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.ObjectSerializeStrategy;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectSerializeStrategy()));
    }
}
