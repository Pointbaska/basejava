package ru.javawebinar.basejava.util;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.model.AbstractSection;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.TextSection;

import java.util.UUID;

import static org.junit.Assert.*;

public class JsonParserTest {

    protected static final Resume RESUME_1;
    protected static final String UUID_1 = UUID.randomUUID().toString();

    static {
        RESUME_1 = ResumeTestData.getInstanceResume(UUID_1, "A");
    }

    @Test
    public void read() {
        String json = JsonParser.write(RESUME_1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(RESUME_1, resume);
    }

    @Test
    public void write() {
        AbstractSection section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, AbstractSection.class);
        System.out.println(json);
        AbstractSection section2 = JsonParser.read(json, AbstractSection.class);
        Assert.assertEquals(section1, section2);
    }
}