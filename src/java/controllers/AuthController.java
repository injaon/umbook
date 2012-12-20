/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.UsuariosDAO;
import form.LogIn;
import form.LogInValidator;
import form.SignUp;
import form.SignUpValidator;
import model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author goks
 */
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
            UsuariosDAO dao = new UsuariosDAO();
            Usuario user = dao.findByUserAndPass(login.getUser(), login.getPass());
            map.addAttribute("user", user);
            return "redirect:wall";
        }
    }

    @RequestMapping(value = "/SignUp", method = RequestMethod.POST)
    public String signup(@ModelAttribute("signup") SignUp signup,
            BindingResult result, ModelMap map) {
        signupValidator.validate(signup, result);
        if (result.hasErrors()) {
            return "index";
        } else {
            
            // agregar a la BD
            return "redirect:wall";
        }
    }
}
