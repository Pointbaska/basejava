package ru.javawebinar.basejava.storage.serializer;

import java.io.IOException;

@FunctionalInterface
public interface SerializeConsumer<T> {

    void write(T t) throws IOException;
}
