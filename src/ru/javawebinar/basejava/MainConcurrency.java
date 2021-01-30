package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        new Thread(() -> {
            Thread current = Thread.currentThread();
            System.out.println(current.getName() + ", " + current.getState());
        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency concurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    concurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
    }

    private void inc() {
        synchronized (this) {
            counter++;
        }
    }
}
