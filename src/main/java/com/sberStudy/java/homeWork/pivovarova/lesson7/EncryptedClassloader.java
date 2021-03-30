package com.sberStudy.java.homeWork.pivovarova.lesson7;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class EncryptedClassloader extends ClassLoader {
    private final String key;
    private final File dir;

    public EncryptedClassloader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            return decrypt(name);
        } catch (Exception ex) {
        }
        return super.findClass(name);
    }

    public Class<?> decrypt(String name) {
        File file = new File(dir, name);
        Class clazz = null;
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            byte[] keyBytes = key.getBytes();
            byte[] dBytes = new byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                dBytes[i] = (byte) (bytes[i] ^ keyBytes[i % keyBytes.length]);
            }
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dBytes);
                 ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)){
                clazz = (Class) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clazz;
    }
    public void encryptClass(Class<?> clazz) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(new File(dir,clazz.getSimpleName()))
        ) {
            objectOutputStream.writeObject(clazz);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            byte[] encrypted = encrypt(bytes);
            fileOutputStream.write(encrypted);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected final byte[] encrypt(byte[] bytes) {
        byte[] keyBytes = key.getBytes();
        byte[] encryptedBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            encryptedBytes[i] = (byte) (bytes[i] ^ keyBytes[i % keyBytes.length]);
        }
        return encryptedBytes;
    }
}
