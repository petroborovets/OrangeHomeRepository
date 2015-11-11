package com.boro.black.component.validation;


import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Created by petroborovets on 10/25/15.
 */
@Component("generalValidation")
public class GeneralValidation {
    public static boolean validateURL(String url) {
        UrlValidator urlValidator = new UrlValidator();
        if (urlValidator.isValid(url))
            return true;
        return false;
    }
}
