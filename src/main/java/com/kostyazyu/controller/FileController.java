package com.kostyazyu.controller;

import com.kostyazyu.model.MyFile;
import com.kostyazyu.service.ParsingService;
import com.kostyazyu.validator.FileValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Controller
public class FileController {

    private static final String INDEX = "index";
    private static final String PARSED = "parsed";

    @Resource
    private FileValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        return INDEX;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String upload(Model model, @Validated MyFile myFile, BindingResult result) throws IOException {

        String returnVal = PARSED;
        if (result.hasErrors()) {
            model.addAttribute("file",result);
            returnVal = INDEX;
        } else {
            MultipartFile multipartFile = myFile.getFile();
            String originalFilename = multipartFile.getOriginalFilename();

            File file = new File(originalFilename);
            multipartFile.transferTo(file);
            ParsingService service = new ParsingService(file);

            model.addAttribute("all_order_sum", service.getAllOrdersSum());
            model.addAttribute("max_customer",  service.getMaxSumCustomer().getName());
            model.addAttribute("max_order_sum", service.getMaxOrderSum());
            model.addAttribute("min_order_sum", service.getMinOrderSum());
            model.addAttribute("orders_count", service.getOrdersCount());
            model.addAttribute("avg_order_sum", service.getAvgOrderSum());
            model.addAttribute("file_name", originalFilename);
        }
        return returnVal;
    }

}