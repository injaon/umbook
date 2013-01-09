/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.UsersDAO;
import form.CommentForm;
import form.CommentValidator;
import form.LogIn;
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

/**
 *
 * @author goks
 */
@Controller
@SessionAttributes({"user"})
public class CommentController {

    private CommentValidator validator = new CommentValidator();

    @RequestMapping(value = "/{type}/Comment", method = RequestMethod.POST)
    public @ResponseBody CommentForm login(@PathVariable("type") String type,
            @ModelAttribute("comment") CommentForm form,
            BindingResult result, ModelMap map) {

        User user = (User) map.get("user");
        validator.setSender(user);
        validator.validate(form, result);
        
        return form;
//        
//        if (result.hasErrors()) {
//            map.addAttribute("msg", "error");
//            map.addAttribute("msg1", form.getBody());
////            return "NOK";
////            return new Object();
//
//        } else {
//            ABM abm = new CommentService();
//            Comment comment = new Comment();
//            comment.setBody(form.getBody());
//            comment.setDate(new Date());
//            comment.setDestiny(new UsersDAO().findById(form.getDestiny()));
//            comment.setOrigin(user);
//            comment.setType(type);
//            abm.add(comment);
//
////            return user;
//        }
        
    }
}
