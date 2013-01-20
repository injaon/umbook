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
import exceptions.PermissionException;
import model.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

public class UsersUtil {
    private User owner;
    private User user;
    
    public void preChecks(ModelAndView mv, ModelMap map, String ownerId) throws PermissionException {
        User owner = null, 
                user = (User) map.get("user");
        
        // pre-checks
        if (user == null) {
            mv.setViewName("redirect:/");
            throw new PermissionException();
        }
        UsersDAO dao = new UsersDAO();
        UserService serv = new UserService();
        try {
            owner = dao.findById(Long.parseLong(ownerId));
            if (!serv.areFriends(user, owner)) {
                map.addAttribute("msg", user.getId() == owner.getId());
                map.addAttribute("msg1", owner.getId());
                mv.setViewName("accessDenied");
                throw new PermissionException();
            }

        } catch (NumberFormatException ex) {
            mv.setViewName("notfound");
            throw new PermissionException();
        } catch (FriendshipException ex) {
            owner = user;
        }
        this.user = user;
        this.owner = owner;
        
        mv.addObject("user", user);
        mv.addObject("owner", owner);

    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
