package ru.javawebinar.basejava;

public class TestDeadLock {
    public static final Object LOCK_1 = new Object();
    public static final Object LOCK_2 = new Object();

    public void getFirst() {
        synchronized (LOCK_1) {
            System.out.println("First lock");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK_2) {
                System.out.print("2");
            }
        }
    }

    public void getSecond() {
        synchronized (LOCK_2) {
            System.out.println("Second lock");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK_1) {
                System.out.print("2");
            }
        }
    }

    public static void main(String[] args) {
        TestDeadLock deadLock = new TestDeadLock();
        new Thread(deadLock::getFirst).start();
        new Thread(deadLock::getSecond).start();
    }
}
