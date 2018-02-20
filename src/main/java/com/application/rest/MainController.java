package com.application.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Secured("authenticated")
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getMainPage() {
        return "main";
    }

    @Secured("authenticated")
    @RequestMapping(value = "/main/add-order", method = RequestMethod.GET)
    public String getAddOrderPage() {
        return "add-order";
    }

    @Secured("authenticated")
    @RequestMapping(value = "/main/add-product", method = RequestMethod.GET)
    public String getAddProductPage() {
        return "add-product";
    }

    @Secured("authenticated")
    @RequestMapping(value = "/main/update-order", method = RequestMethod.GET)
    public String getUpdateOrderPage() {
        return "update-order";
    }

    @Secured("authenticated")
    @RequestMapping(value = "/main/update-product", method = RequestMethod.GET)
    public String getUpdateProductPage() {
        return "update-product";
    }

    @Secured("authenticated")
    @RequestMapping(value = "/main/list", method = RequestMethod.GET)
    public String getListPage() {
        return "list";
    }


}
