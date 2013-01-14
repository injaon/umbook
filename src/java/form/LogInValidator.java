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
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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
