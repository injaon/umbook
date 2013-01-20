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

package dao;

import java.util.Set;
import model.Album;
import model.HibernateUtil;
import model.User;
import org.hibernate.Session;

public class AlbumsDAO {

    public Set<Album> getAlbumsByOwner(User owner) {
        Set<Album> res;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        
        s.refresh(owner);
        res = owner.getAlbums();
        for(Album a: res) {
            a.getName();
        }
        
        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return res;    
    }
}
