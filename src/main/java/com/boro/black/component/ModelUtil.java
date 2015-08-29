package com.boro.black.component;

import com.boro.black.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

/**
 * Created by petroborovets on 6/27/15.
 */
@Component("modelUtil")
public class ModelUtil {
    @Autowired
    private UserService userService;

    static Logger log = Logger.getLogger(ModelUtil.class.getName());

    public ModelMap warp(ModelMap model) {

        com.boro.black.entity.User signedInUser = userService.getLoggedInUser();

        if (signedInUser == null) {
            String errorMsg = "Failed to find user.";
            log.error(errorMsg);
        } else {
            String userFirstName = signedInUser.getFirstName();
            String userLastName = signedInUser.getLastName();

            model.addAttribute("userFirstName", userFirstName);
            model.addAttribute("userLastName", userLastName);
        }

        return model;
    }
}
