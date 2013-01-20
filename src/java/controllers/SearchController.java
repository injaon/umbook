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
import form.SearchForm;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes
public class SearchController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(@ModelAttribute("search") SearchForm form,
            BindingResult result, ModelMap map) {
        ModelAndView mv = new ModelAndView("search");

        String input = form.getInput();
        if (input.length() == 0) {
            result.rejectValue("input", "search.input.empty");
        }

        UsersDAO dao = new UsersDAO();
        Set<User> results = new HashSet<User>();
        List<User> dummyList;
        User dummy;

        for (String entry : input.split("\\s+")) {
            try { /* Is it a mail? */
                new InternetAddress(input, true);
                dummy = dao.findByEmail(entry);
                if (dummy != null) {
                    results.add(dummy);
                }
            } catch (AddressException ex) {
                /* It is not a mail, big deal! shut up exception! */
            }
            /* is it a username?? */
            dummy = dao.findByUsername(entry);
            if (dummy != null) {
                results.add(dummy);
            }

            /* Are you nasty? */
            if (entry.equalsIgnoreCase("female")) {
                dummyList = dao.find(null, null, null, null, "Female");
                if (dummyList != null && dummyList.size() > 0) {
                    results.addAll(dummyList);
                }

            } else if (entry.equalsIgnoreCase("male")) {
                dummyList = dao.find(null, null, null, null, "Male");
                if (dummyList != null && dummyList.size() > 0) {
                    results.addAll(dummyList);
                }
            }

            /* mmhh.. nothing ah? You write this name! */
            dummyList = dao.find(null, null, entry, entry, null);
            if (dummyList != null && dummyList.size() > 0) {
                results.addAll(dummyList);
            }
        }
        mv.addObject("users", results);
        return mv;
    }
}
