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
