package com.robin.testdemo.io;

import java.io.Serializable;

public class Bean implements Serializable {
    private int index;
    private String name;
    public static int state;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
