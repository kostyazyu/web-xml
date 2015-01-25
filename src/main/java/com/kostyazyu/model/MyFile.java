package com.kostyazyu.model;

import org.springframework.web.multipart.MultipartFile;

public class MyFile {

    MultipartFile file;
    private double sum;


    public MultipartFile getMyFile() {
        return file;
    }

    public void setMyFile(MultipartFile file) {
        this.file = file;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}

