package com.kostyazyu.model;

import org.springframework.web.multipart.MultipartFile;

public class MyFile {
    MultipartFile file;
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

