/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.UsersDAO;
import form.CommentForm;
import form.CommentValidator;
import form.LogIn;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author goks
 */
@Controller
@SessionAttributes({"user"})
public class CommentController {

    private CommentValidator validator = new CommentValidator();

    @RequestMapping(value = "/{type}/Comment", method = RequestMethod.POST)
    public String login(@PathVariable("type") String type,
            @ModelAttribute("comment") CommentForm comment,
            BindingResult result, ModelMap map) {
        
        validator.setSender((User) map.get("user"));
        validator.validate(comment, result);

        if (result.hasErrors()) {
            map.addAttribute("msg", "error");
            map.addAttribute("msg1", comment.getBody());
            return "notfound";
        } else {

//            UsersDAO dao = new UsersDAO();
//            User user = dao.findByUserAndPass(login.getUser(), login.getPass());
//            map.addAttribute("user", user);
//            
//            return String.format("redirect:wall/%d", user.getId());
            map.addAttribute("msg", comment.getDestiny());
            return "notfound";
        }
    }
}
