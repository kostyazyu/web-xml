package com.kostyazyu.validator;

import com.kostyazyu.model.MyFile;
import com.kostyazyu.utils.MyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;


public class FileValidator implements Validator {

    @Value("classpath:/customers.xsd")
    private Resource schemaResource;


    public boolean supports(Class<?> paramClass) {
        return MyFile.class.equals(paramClass);
    }

    public void validate(Object obj, Errors errors) {

        MyFile myFile = (MyFile) obj;

        if (myFile.getMyFile().getSize() == 0) {
            errors.rejectValue("myFile", "valid.file");
        }
        else if (Double.compare(myFile.getSum(), 0) == 0){
            errors.rejectValue("myFile", "valid.sum");
        }
        else {
            try {
                File file = MyUtils.multipartToFile(myFile.getMyFile());
                File schemaFile = schemaResource.getFile();
                schemaValidation(file, schemaFile, errors);
            } catch (IOException e) {
                errors.rejectValue("myFile", "valid.fileNotValid", new Object[]{e.getMessage()}, "File is not valid");
            }
        }
    }

    public boolean schemaValidation(File file, File schemaFile, Errors errors) {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        try {
            Schema schema = factory.newSchema(schemaFile);
            javax.xml.validation.Validator validator = schema.newValidator();
            Source source = new StreamSource(file);
            validator.validate(source);
            return true;
        } catch (SAXException | IOException e) {
            errors.rejectValue("myFile", "valid.fileNotValid", new Object[]{e.getMessage()}, "File is not valid");
        }
        return false;
    }


}
