package com.oproom.model;

/**
 * Created by Cezar Carneiro on 24/1/2018.
 */

public class DirectoryInfo {

    private String absolutePath;
    private String name;
    private long length;
    private FileEntryInfo[] children;

    public DirectoryInfo() {

    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public FileEntryInfo[] getChildren() {
        return children;
    }

    public void setChildren(FileEntryInfo[] children) {
        this.children = children;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
