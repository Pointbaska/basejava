package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.sql.SqlHelper;
import ru.javawebinar.basejava.util.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(new SqlStorage(new SqlHelper(Config.getInstance().getDbUrl(),
                Config.getInstance().getDbUser(), Config.getInstance().getDbPassword())));
    }
}
