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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import model.Comment;
import model.HibernateUtil;
import model.User;
import org.hibernate.Session;

public class CommentsDAO {

    private Session session;

//    public List<Comment> getComentarios(long dest_id) {
//        List<Comment> comments = new ArrayList<Comment>();
//        Comment c = new Comment(1, 1, "wall", "propio", new Date());
//        comments.add(c);
//        c = new Comment(6, 1, "wall", "Desde bender", new Date());
//        comments.add(c);
//        return comments;
                //        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
                //        if (keepAlive) {
                //            session = s;
                //
                //        }
                //        s.beginTransaction();
                //
                //        User u = (User) s.get(User.class, dest_id);
                ////        Set<Comment> comentarios = u.getCommentsForDestino();
                //                    
                //        /*
                //        Iterator<Comment> iterator = comentarios.iterator();
                //        Comment c = null;
                //        if (iterator.hasNext()) {
                //        c = iterator.next();
                //        System.out.println("-------------" + c.getCuerpo());
                //        
                //        }
                //        
                //         */
                //        if (!keepAlive) {
                //            System.out.println("CERRANDO SESSION");
                //            s.getTransaction().commit();
                //            HibernateUtil.getSessionFactory().close();
                //        }
                //
                //        if (comentarios == null || comentarios.isEmpty()) {
                //            return null;
                //        }
                //
                //        return comentarios;
//    }
}
