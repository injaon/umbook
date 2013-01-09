/*
 * Copyright (C) 2013 Gabriel Lopez <gabriel.marcos.lopez@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package form;

import dao.UsersDAO;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import services.DateUtil;

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


        if (!signup.getBirth().equals("")) {
            Calendar cal = Calendar.getInstance();
            cal.setLenient(false);
            try {
                cal.setTime(new DateUtil().parse(signup.getBirth()));
                cal.getTime();

            } catch (ParseException ex) {
                errors.reject("birth", "signup.birth.invalid");
            } catch (IllegalArgumentException e) {
                errors.reject("birth", "signup.birth.invalid");
            }
        }
    }
}
