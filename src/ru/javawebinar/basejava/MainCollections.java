package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MainCollections {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String NAME_1 = "D";
    private static final String NAME_2 = "C";
    private static final String NAME_3 = "B";

    private static final Resume RESUME_1 = new Resume(NAME_1, UUID_1);
    private static final Resume RESUME_2 = new Resume(NAME_2, UUID_2);
    private static final Resume RESUME_3 = new Resume(NAME_3, UUID_3);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);

//        for (Resume resume : collection) {
//            System.out.println(resume);
//            if (Objects.equals(resume.getUuid(), UUID_1)) {
//                collection.remove(resume);
//            }
//        }
        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            System.out.println(resume);
            if (Objects.equals(resume.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);
        System.out.println("__________________________");
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
    }
}
