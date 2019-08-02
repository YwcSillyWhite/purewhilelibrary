package com.purewhite.ywc.purewhitelibrary.network.okhttp.bean;

import java.io.File;

public class FileInput {


    public FileInput(String key, String filename, File file) {
        this.key = key;
        this.filename = filename;
        this.file = file;
    }

    private String key;
    private String filename;
    private File file;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
