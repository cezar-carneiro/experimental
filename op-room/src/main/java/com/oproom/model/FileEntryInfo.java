package com.oproom.model;

/**
 * Created by Cezar Carneiro on 24/1/2018.
 */

public class FileEntryInfo {

    private String name;
    private long length;
    private long lastModified;
    private boolean isDir;

    public FileEntryInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }
}
