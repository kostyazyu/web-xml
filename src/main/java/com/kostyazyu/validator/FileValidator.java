package com.kostyazyu.validator;

import com.kostyazyu.model.MyFile;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class FileValidator implements Validator {

    public boolean supports(Class<?> paramClass) {
        return MyFile.class.equals(paramClass);
    }

    public void validate(Object obj, Errors errors) {
        MyFile myFile = (MyFile) obj;
        if (myFile.getFile().getSize() == 0) {
            errors.rejectValue("file", "valid.file", "Test");
        }
    }
}
