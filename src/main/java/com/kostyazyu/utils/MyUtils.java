package com.kostyazyu.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MyUtils {
    public static File multipartToFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        File file = new File(originalFilename);
        multipartFile.transferTo(file);
        return file;
    }
}