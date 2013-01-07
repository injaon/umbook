/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author goks
 */
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
