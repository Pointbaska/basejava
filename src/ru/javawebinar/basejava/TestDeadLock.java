package ru.javawebinar.basejava;

public class TestDeadLock {
    public static final Object LOCK_1 = new Object();
    public static final Object LOCK_2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> getDeadLock(LOCK_1, LOCK_2)).start();
        new Thread(() -> getDeadLock(LOCK_2, LOCK_1)).start();
    }

    public static void getDeadLock(Object lock1, Object lock2) {
        String thread = Thread.currentThread().getName();
        System.out.println("Ожидание захвата " + lock1 + " монитора потоком " + thread);
        synchronized (lock1) {
            System.out.println("Захват " + lock1 + " монитора потоком " + thread);
            System.out.println("Ожидание захвата " + lock2 + " монитора потоком " + thread);
            synchronized (lock2) {
                System.out.println("Захват " + lock2 + " монитора потоком " + thread);
            }
        }
    }
}
