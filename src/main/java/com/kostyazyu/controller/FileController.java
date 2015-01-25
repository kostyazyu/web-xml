package com.kostyazyu.controller;

import com.kostyazyu.model.MyFile;
import com.kostyazyu.service.CustomerService;
import com.kostyazyu.utils.MyUtils;
import com.kostyazyu.validator.FileValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String upload(Model model, @ModelAttribute("myFile") @Validated MyFile myFile, BindingResult result) throws IOException {

        String returnVal = PARSED;
        if (result.hasErrors()) {
            returnVal = INDEX;
        } else {
            MultipartFile multipartFile = myFile.getMyFile();
            File file = MyUtils.multipartToFile(multipartFile);

            CustomerService service = new CustomerService(file);

            model.addAttribute("allOrderSum", service.getAllOrdersSum());
            model.addAttribute("maxSumCustomer",  service.getMaxSumCustomer().getName());
            model.addAttribute("maxOrderSum", service.getMaxOrderSum());
            model.addAttribute("minOrderSum", service.getMinOrderSum());
            model.addAttribute("ordersCount", service.getOrdersCount());
            model.addAttribute("avgOrderSum", service.getAvgOrderSum());
            model.addAttribute("fileName", file.getName());
            model.addAttribute("customersWithOrderSumGraterThan",
                    service.getCustomersWithOrderSumGraterThan(myFile.getSum()));
            model.addAttribute("maxOrderSumRequested", myFile.getSum());

        }
        return returnVal;
    }

}