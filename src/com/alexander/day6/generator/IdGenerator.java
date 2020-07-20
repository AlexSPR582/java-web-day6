package com.alexander.day6.generator;

public class IdGenerator {
    public static int id = 0;

    public static int generateId() {
        id++;
        return id;
    }
}
