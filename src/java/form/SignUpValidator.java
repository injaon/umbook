/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import dao.UsersDAO;
import java.util.Calendar;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author goks
 */
public class SignUpValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return SignUp.class.isAssignableFrom(clazz);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "signup.user.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "signup.email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "signup.nombre.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "signup.apellido.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "signup.password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repassword", "signup.repassword.required");

        SignUp signup = (SignUp) o;
        UsersDAO dao = new UsersDAO();
        if (!signup.getPassword().equals(signup.getRepassword())) {
            errors.rejectValue("repassword", "signup.repassword.mismatch");
        }

        try {
            new InternetAddress(signup.getEmail()).validate();
            if (dao.findByEmail(signup.getEmail()) != null) {
                errors.reject("email", "signup.email.exisists");
            }
        } catch (AddressException ex) {
            errors.rejectValue("email", "signup.email.invalid");
        }
        if (dao.findByUsername(signup.getUser()) != null) {
            errors.rejectValue("user", "signup.user.exists");
        }

        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.set(signup.getYear(), signup.getMonth().ordinal(), signup.getDay());

        try {
            cal.getTime();
        } catch (IllegalArgumentException e) {
            errors.reject("months", "signup.date.invalid");
        }



    }
//    public static void main(String[] args) throws AddressException {
//        new InternetAddress("sdkjfn").validate();
//    }
}
