package com.application.rest;

import com.application.rest.Exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//контроллер для страницы авторизации и главной

@Controller
public class ExceptionController {


    //обработчик ошибки для запроса /*
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String getIndexPageException(@PathVariable("name") String name) {
        if (name.equals("login") || name.equals("logout") || name.equals("main")) {
            throw new NotFoundException();
        }
        return "errors/error";
    }

    //обработчик ошибки для запроса /login/*
    @RequestMapping(value = "/login/{name}", method = RequestMethod.GET)
    public String getLoginPageException(@PathVariable("name") String name) {
        if (name.equals("")) {
            throw new NotFoundException();
        }
        return "errors/error";
    }

    //обработчик ошибки для запроса /logout/*
    @RequestMapping(value = "/logout/name", method = RequestMethod.GET)
    public String getLogoutPageException(@PathVariable("name") String name) {
        if (name.equals("")) {
            throw new NotFoundException();
        }
        return "errors/error";
    }

    //обработчик ошибки для запроса /main/*
    @RequestMapping(value = "/main/{name}", method = RequestMethod.GET)
    public String getMainPageException(@PathVariable("name") String name) {
        if (name.equals("add-order") || name.equals("add-product") ||
                name.equals("update-order") || name.equals("update-product") || name.equals("list")) {
            throw new NotFoundException();
        }
        return "errors/error";
    }

    @RequestMapping(value = "/main/add-product/{name}", method = RequestMethod.GET)
    public String getAddProductPageException(@PathVariable("name") String name) {
        if ((name.equals("")) ) {
            throw new NotFoundException();
        }
        return "errors/error";
    }

    @RequestMapping(value = "/main/add-order/{name}", method = RequestMethod.GET)
    public String getAddOrderPageException(@PathVariable("name") String name) {
        if ((name.equals("")) ) {
            throw new NotFoundException();
        }
        return "errors/error";
    }

    @RequestMapping(value = "/main/update-product/{name}", method = RequestMethod.GET)
    public String getUpdateProductPageException(@PathVariable("name") String name) {
        if ((name.equals("")) ) {
            throw new NotFoundException();
        }
        return "errors/error";
    }

    @RequestMapping(value = "/main/update-order/{name}", method = RequestMethod.GET)
    public String getUpdateOrderPageException(@PathVariable("name") String name) {
        if ((name.equals("")) ) {
            throw new NotFoundException();
        }
        return "errors/error";
    }

    @RequestMapping(value = "/main/list/{name}", method = RequestMethod.GET)
    public String getListPageException(@PathVariable("name") String name) {
        if ((name.equals("")) ) {
            throw new NotFoundException();
        }
        return "errors/error";
    }
}