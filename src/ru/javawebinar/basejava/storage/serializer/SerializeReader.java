package ru.javawebinar.basejava.storage.serializer;

import java.io.IOException;

@FunctionalInterface
public interface SerializeReader {

    void read() throws IOException;
}
