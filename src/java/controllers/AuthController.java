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
import form.LogIn;
import form.LogInValidator;
import form.SignUp;
import form.SignUpValidator;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

@Controller
@SessionAttributes({"login", "signup", "user"})
public class AuthController {

    private SignUpValidator signupValidator = new SignUpValidator();
    private LogInValidator loginValidator = new LogInValidator();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelMap map) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("login", new LogIn());
        mv.addObject("signup", new SignUp());
        return mv;
    }

    @RequestMapping(value = "/LogIn", method = RequestMethod.POST)
    public String login(@ModelAttribute("login") LogIn login,
            BindingResult result, ModelMap map) {

        loginValidator.validate(login, result);
        if (result.hasErrors()) {
            return "index";

        } else {
            UsersDAO dao = new UsersDAO();
            User user = dao.findByUserAndPass(login.getUser(), login.getPass());
            map.addAttribute("user", user);
            return String.format("redirect:wall/%d", user.getId());
        }
    }

    @RequestMapping(value = "/SignUp", method = RequestMethod.POST)
    public String signup(@ModelAttribute("signup") SignUp signup,
            BindingResult result, ModelMap map) {

        signupValidator.validate(signup, result);
        if (result.hasErrors()) {
            return "index";
            
        } else {
            UserService serv = new UserService();
            User user = serv.fromForm(signup);
            serv.add(user);
            map.addAttribute("user", user);
            return String.format("redirect:wall/%d", user.getId());
        }
    }
}
