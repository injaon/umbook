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
package controllers;

import dao.UsersDAO;
import form.CommentForm;
import form.CommentValidator;
import java.util.Date;
import model.Comment;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import services.ABM;
import services.CommentService;

@Controller
@SessionAttributes({"user"})
public class CommentController {

    private CommentValidator validator = new CommentValidator();

    @RequestMapping(value = "/{type}/comment", method = RequestMethod.POST)
    public @ResponseBody
    CommentForm comment(@PathVariable("type") String type,
            @ModelAttribute("comment") CommentForm form,
            BindingResult result, ModelMap map) {

        form.setType(type);
        User user = (User) map.get("user");
        validator.setSender(user);
        validator.validate(form, result);

        if (result.hasErrors()) {
            form.setType("error");
            form.setBody("Error loco");
            
        } else {
            ABM abm = new CommentService();
            Comment comment = new Comment();
            comment.setBody(form.getBody());
            comment.setDate(new Date());
            comment.setDestiny(new UsersDAO().findById(form.getDestiny()));
            comment.setOrigin(user);
            comment.setType(type);
            abm.add(comment);
        }


        return form;
    }
}
