/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author goks
 */
@Controller
@SessionAttributes({"user"})
public class WallController {

    @RequestMapping(value = "wall", method = RequestMethod.GET)
    public ModelAndView wall(ModelMap map) {
        Usuario user = (Usuario) map.get("user");
        
        if (user == null) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView mv = new ModelAndView("wall");
        mv.addObject("user", user);
        return mv;
    }
}
