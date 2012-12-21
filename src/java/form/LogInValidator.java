/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.UsersDAO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author goks
 */
public class LogInValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return LogIn.class.isAssignableFrom(clazz);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "login.user.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "login.pass.required");
        LogIn login = (LogIn) o;
        UsersDAO dao = new UsersDAO();
        if (dao.findByUserAndPass(login.getUser(), login.getPass()) == null) {
            errors.rejectValue("user", "login.failed");
        }

    }
}
