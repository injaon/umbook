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

import dao.AlbumsDAO;
import dao.UsersDAO;
import form.AlbumForm;
import java.util.Set;
import model.Album;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import services.AlbumService;

@Controller
@SessionAttributes({"user"})
public class AlbumController {

    /* list albums in wall */
    @RequestMapping(value = "/photos/albums/{ownerId}", method = RequestMethod.GET)
    public ModelAndView list_albums(@PathVariable("ownerId") String ownerId, ModelMap map) {
        ModelAndView mv = new ModelAndView("albums");
        AlbumsDAO dao = new AlbumsDAO();
        User owner = new UsersDAO().findById(Long.parseLong(ownerId));
        Set<Album> albums = dao.getAlbumsByOwner(owner);
        Album newAlbum = new Album("New Album...", "Create a new album with new photos.", null);
        newAlbum.setImage("http://artandmobile.com/files/SuperAlbumIcon.png");

        mv.addObject("owner", owner);
        mv.addObject("albums", albums);
        mv.addObject("newAlbum", newAlbum);
        map.addAttribute("albumForm", new AlbumForm());
        return mv;
    }

    /* create new new album */
    @RequestMapping(value = "/album/new", method = RequestMethod.POST)
    public String createAlbum(@ModelAttribute("albumForm") AlbumForm form,
            BindingResult result, ModelMap map) {

        /* Simple validation */
        if (form.getName() == null || form.getName().equals("")) {
            return "redirect:/error";
        }
        
        /* store new album */
        User user = (User) map.get("user");
        AlbumService serv = new AlbumService();
        Album album = serv.fromForm(form);
        album.setOwner(user);
        serv.add(album);
        return "redirect:/photo/all/" + album.getId();
    }
}
