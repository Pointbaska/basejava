package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPS_DIR = new File("config\\resumes.properties");
    private static final Config INSTANCE = new Config();

    private Properties props;
    private File storageDir;
    private SqlStorage sqlStorage;

    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS_DIR)) {
            props = new Properties();
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            sqlStorage = new SqlStorage(props.getProperty("db.url"),
                    props.getProperty("db.user"),
                    props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS_DIR.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public SqlStorage getSqlStorage() {
        return sqlStorage;
    }
}
