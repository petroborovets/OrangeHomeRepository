package com.boro.orange.controller;

import com.boro.orange.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model) {

        return "home";
    }

}
