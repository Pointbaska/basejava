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
        System.out.println(thread + " is waiting for monitor capture " + lock1);
        synchronized (lock1) {
            System.out.println(lock1 + " locked by " + thread);
            System.out.println(thread + " is waiting for monitor capture " + lock2);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println(lock1 + " and " + lock2 + " locked by " + thread);
            }
        }
    }
}
