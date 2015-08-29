package com.boro.black.controller;

import com.boro.black.component.ModelUtil;
import com.boro.black.entity.User;
import com.boro.black.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by petroborovets on 6/20/15.
 */
@Controller
public class UserProfileController {
    @Autowired
    ModelUtil modelUtil;
    @Autowired
    UserService userService;

    static Logger log = Logger.getLogger(UserProfileController.class.getName());

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public String userProfileHome(ModelMap model) {

        User loggedInUser = userService.getLoggedInUser();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        model.addAttribute("userCreationDate", df.format(loggedInUser.getCreateDate()));
        modelUtil.warp(model);

        log.info("Loading userProfile.jsp");
        return "userProfile";
    }
}
