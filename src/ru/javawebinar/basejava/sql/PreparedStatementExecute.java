package ru.javawebinar.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementExecute<T> {
    T executePs(PreparedStatement ps) throws SQLException;
}
