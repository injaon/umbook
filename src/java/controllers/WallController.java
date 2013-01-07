/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.UsersDAO;
import exceptions.FriendshipException;
import form.CommentForm;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

/**
 *
 * @author goks
 */
@Controller
@SessionAttributes({"user"})
public class WallController {

    @RequestMapping(value = "/wall/{owner}", method = RequestMethod.GET)
    public ModelAndView wall(@PathVariable("owner") String ownerId, ModelMap map) {
        User owner = null, user = (User) map.get("user");
        // pre-checks
        if (user == null) {
            return new ModelAndView("redirect:/");
        }
        UsersDAO dao = new UsersDAO();
        UserService serv = new UserService();
        try {
            owner = dao.findById(Long.parseLong(ownerId));
            if (!serv.areFriends(user, owner)) {
                map.addAttribute("msg", user.getId() == owner.getId());
                map.addAttribute("msg1", owner.getId());
                return new ModelAndView("accessDenied");
            }

        } catch (NumberFormatException ex) {
            return new ModelAndView("notfound");
        } catch (FriendshipException ex) {
            owner = user;
        }

        // create wall
        ModelAndView mv = new ModelAndView("wall");
        mv.addObject("user", user);
        mv.addObject("owner", owner);
        mv.addObject("comment", new CommentForm());
//        mv.addObject("friends", owner.getFriends());
        
        
        
        return mv;
    }

    public static void main(String[] args) {
        System.out.println(Long.parseLong("1asd"));
//        UsersDAO dao = new UsersDAO();
//        User u = dao.findById(Long.getLong("1"));
//        
//        System.out.println(u.getEmail());
    }
}
