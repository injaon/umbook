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
import exceptions.FriendshipException;
import form.CommentForm;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import services.UserService;

public class CommentValidator implements Validator {

    private User sender;

    public boolean supports(Class<?> clazz) {
        return CommentForm.class.isAssignableFrom(clazz);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "destiny", null);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", null);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body",
                "comment.body.required");

        /* check comment type */
        CommentForm form = (CommentForm) o;
        String type = form.getType();
        UsersDAO dao = new UsersDAO();
        UserService serv = new UserService();

        if (type.equals("wall") || type.equals("mp")) {
            User dest = dao.findById(form.getDestiny());
            try {
                if (!serv.areFriends(this.sender, dest)) {
                    errors.rejectValue("destiny", "comment.destiny.accessdenied");
                }

            } catch (FriendshipException ex) {
                if (!type.equals("wall")) {
                    errors.rejectValue("type", "comment.type.invalid");
                }
            }
        } else {
            errors.rejectValue("type", "comment.type.invalid");
        }
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
