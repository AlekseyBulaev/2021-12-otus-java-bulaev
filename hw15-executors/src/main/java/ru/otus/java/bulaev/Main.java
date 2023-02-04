package ru.otus.java.bulaev;

public class Main {
    public static String thread = "thread-2";
    public static void main(String[] args) {
        Main main = new Main();
        new Thread(() -> main.action("thread-1")).start();
        new Thread(() -> main.action("thread-2")).start();
    }


    public synchronized void action(String threadName) {
        for(int i = 1; i < 11; i++) {
            while (thread.equals(threadName)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            printResult(threadName, i);
            notifyAll();
        }
        for(int i = 9; i > 0 ; i--) {
            while (thread.equals(threadName)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            printResult(threadName, i);
            notifyAll();
        }
    }

    private void printResult(String threadName, int i) {
            System.out.println(threadName + ": " + i);
            thread = threadName;
    }
}