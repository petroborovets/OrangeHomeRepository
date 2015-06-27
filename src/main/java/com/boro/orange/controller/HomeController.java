package com.boro.orange.controller;

import com.boro.orange.component.ModelUtil;
import com.boro.orange.component.validation.RegistrationValidation;
import com.boro.orange.dao.implementation.UserDAO;
import com.boro.orange.dto.UserDTO;
import com.boro.orange.entity.GismeteoData;
import com.boro.orange.entity.SecurityRole;
import com.boro.orange.entity.User;
import com.boro.orange.entity.crawler.Email;
import com.boro.orange.entity.crawler.EmailContext;
import com.boro.orange.exception.NonUniqueElementException;
import com.boro.orange.service.SecurityRoleService;
import com.boro.orange.service.UserService;
import com.boro.orange.service.crawler.EmailService;
import com.boro.orange.utils.GismeteoUtil;
import com.boro.orange.utils.StaticValuesUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;
    @Autowired
    SecurityRoleService securityRoleService;
    @Autowired
    ModelUtil modelUtil;

    static Logger log = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model) {

        log.info("Loading home.jsp");
        modelUtil.warp(model);

        return "home";
    }

    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
    public String loadLoginForm(ModelMap model, @RequestParam(value = "errorMessage", required = false) String error) {
        log.info("Loading loginForm.jsp");

        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid username or password.");
        }

        return "loginForm";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String loadAccessDeniedPage(ModelMap model) {
        log.info("Loading accessDenied.jsp");

        return "accessDenied";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String loadRegistrationForm(ModelMap model) {
        log.info("Loading registration.jsp");

        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);

        return "registration";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerNewUser(@ModelAttribute(value = "user") UserDTO userDTO, BindingResult result, ModelMap model) {
        log.info("Creating new user [" + userDTO.getEmail() + "]");

        RegistrationValidation.validate(userDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("userDTO", new UserDTO());

            StringBuilder errorsString = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                errorsString.append(error.getDefaultMessage() + "\n");
            }

            model.addAttribute("errorMessage", errorsString.toString());
            log.warn("Failed to create new user [" + errorsString.toString() + "].");
            return "registration";
        }

        // Creating new User
        User userToRegister = new User();
        userToRegister.setEmail(userDTO.getEmail());
        userToRegister.setFirstName(userDTO.getFirstName());
        userToRegister.setLastName(userDTO.getLastName());

        // Trying to get existing user security role
        SecurityRole userSecurityRole = null;
        for (SecurityRole securityRole : securityRoleService.getAllElements()) {
            if (securityRole.getName().equals(StaticValuesUtil.securityRoleUser)) {
                userSecurityRole = securityRole;
                break;
            }
        }
        if (userSecurityRole == null) {
            userSecurityRole = new SecurityRole();
            userSecurityRole.setName(StaticValuesUtil.securityRoleUser);
            try {
                securityRoleService.addElement(userSecurityRole);
            } catch (NonUniqueElementException e) {
                String errorMsg = "Failed to create new SecurityRole. " +
                        "Security role [" + userSecurityRole.getName() + "] already exist.";
                model.addAttribute("errorMessage", errorMsg);
                log.error(errorMsg);
                return "registration";
            }
        }

        // Creating SecurityRole set
        // Adding ROLE_USER to set
        // Attaching set to registered user
        Set<SecurityRole> securityRoleSet = userToRegister.getRoles();
        if (securityRoleSet == null) {
            securityRoleSet = new HashSet<SecurityRole>();
        }
        securityRoleSet.add(userSecurityRole);
        userToRegister.setRoles(securityRoleSet);

        // Setting user password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        userToRegister.setPassword(hashedPassword);

        try {
            userService.addElement(userToRegister);
        } catch (NonUniqueElementException e) {
            String errorMsg = "Failed to create new User. " + e.getMessage();
            log.error(errorMsg);
            model.addAttribute("userDTO", new UserDTO());

            model.addAttribute("errorMessage", errorMsg);
            return "registration";
        }

        modelUtil.warp(model);
        log.info("New User [" + userToRegister + "] created");
        return "home";
    }

}
