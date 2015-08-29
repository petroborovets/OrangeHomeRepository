package com.boro.black.component.validation;

import com.boro.black.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * Created by petroborovets on 5/11/15.
 */
@Component("registrationValidator")
public class RegistrationValidation {

    public static void validate(Object target, Errors errors) {
        UserDTO registration = (UserDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
                "NotEmpty.registration.firstName",
                "User Name must not be Empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
                "NotEmpty.registration.lastName",
                "Last Name must not be Empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "NotEmpty.registration.email",
                "Email must not be Empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "NotEmpty.registration.password",
                "Password must not be Empty.");

        String firstName = registration.getFirstName();
        if ((firstName.length()) > 50) {
            errors.rejectValue("firstName",
                    "lengthOfUser.registration.firstName",
                    "User Name must not more than 50 characters.");
        }

        String lastName = registration.getLastName();
        if ((lastName.length()) > 50) {
            errors.rejectValue("lastName",
                    "lengthOfUser.registration.userName",
                    "User Name must not more than 50 characters.");
        }
        if (!(registration.getPassword()).equals(registration
                .getConfirmPassword())) {
            errors.rejectValue("password",
                    "matchingPassword.registration.password",
                    "Password and Confirm Password Not match.");
        }
    }
}
