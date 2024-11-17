package ru.job4j.org.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String fileName;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(fileName);
        byte[] buffer = new byte[512];
        int downloadByte, totalBytes;
        totalBytes = 0;
        long totalTime;
        long startTime = System.currentTimeMillis();
        try (InputStream input = new URL(url).openStream();
             FileOutputStream out = new FileOutputStream(file)) {
            while ((downloadByte = input.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, buffer.length);
                totalBytes = totalBytes + downloadByte;
                totalTime = System.currentTimeMillis() - startTime;
                if (totalBytes >= speed && totalTime <= 1000) {
                    Thread.sleep(1000 - totalTime);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

            private static void check(String[] args) {
                if (args.length == 0) {
                    throw new IllegalArgumentException("Arguments are not assigned to the main method");
                }
                String url = args[0];
                int speed = Integer.parseInt(args[1]);
                String fileName = args[2];
                try {
                    new URL(url).toURI();
                } catch (Exception e) {
                    throw new IllegalArgumentException("The URL " + url + " isn't valid.");
                }
                if (speed <= 0) {
                    throw new IllegalArgumentException("This value: " + speed + " is incorrect");
                }

                if (!fileName.contains(".")) {
                    throw new IllegalArgumentException("Please enter a valid file name");
                }

            }

            public static void main(String[] args) throws InterruptedException {
                check(args);
                String url = args[0];
                int speed = Integer.parseInt(args[1]);
                String fileName = args[2];
                Thread wget = new Thread(new Wget(url, speed, fileName));
                wget.start();
                wget.join();
            }
        }