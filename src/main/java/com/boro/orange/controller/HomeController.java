package com.boro.orange.controller;

import com.boro.orange.dao.implementation.UserDAO;
import com.boro.orange.entity.crawler.Email;
import com.boro.orange.entity.crawler.EmailContext;
import com.boro.orange.exception.NonUniqueElementException;
import com.boro.orange.service.crawler.EmailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @Autowired
    UserDAO userDAO;
    @Autowired
    EmailService emailService;

    static Logger log = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model) {

        log.info("HomeController started, loading home.jsp");

        return "home";
    }

    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
    public String loadLoginForm(ModelMap model) {

        log.info("HomeController started, loading loginGorm.jsp");

        return "loginForm";
    }

}
