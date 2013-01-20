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
import java.util.List;
import model.HibernateUtil;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsersDAO {

    public User findById(long id) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        User res = (User) s.get(User.class, id);
        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return res;
    }

    public User findByUsername(String username) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

        String sttm = String.format("from User as u where u.name='%s'",
                username);
        Query q = s.createQuery(sttm);
        User res = (User) q.uniqueResult();

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return res;
    }

    public User findByEmail(String email) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

        String sttm = String.format("from User as u where u.email='%s'",
                email);
        Query q = s.createQuery(sttm);
        User res = (User) q.uniqueResult();

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return res;
    }

    public User findByUserAndPass(String user, String pass) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

        String sttm = String.format("from User as u where u.name='%s'"
                + " and u.password='%s'", user, pass);
        Query q = s.createQuery(sttm);
        User res = (User) q.uniqueResult();

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return res;
    }

    public List<User> find(String username, String email,
            String realName, String lastName, String gender) {
        StringBuilder sttm = new StringBuilder("FROM User AS u WHERE ( ");
        if (username != null) {
            sttm.append(String.format("LOWER(u.user) LIKE '%%%s%%' OR ",
                    username));
        }
        if (email != null) {
            sttm.append(String.format("LOWER(u.email) LIKE '%%%s%%' OR ",
                    email));
        }
        if (realName != null) {
            sttm.append(String.format("LOWER(u.firstName) LIKE '%%%s%%' OR ",
                    realName));
        }
        if (lastName != null) {
            sttm.append(String.format("LOWER(u.lastName) LIKE '%%%s%%' OR ",
                    lastName));
        }
        if (gender != null) {
            sttm.append(String.format("u.gender = '%s' OR ", gender));
        }
        
        int i = sttm.lastIndexOf("OR");
        sttm.delete(i, i + 2);
        sttm.append(")");
        
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

        Query q = s.createQuery(sttm.toString());
        //        q.setFirstResult(first);
        //        q.setMaxResults(max);
        List<User> res = (ArrayList<User>) q.list();

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return res;
    }

//    public List<User> getFriendRequests() {
//        if (user == null) {
//            return null;
//        }
//
//        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
//        s.beginTransaction();
//
//        String sttm = String.format(
//                "SELECT usuariosByOrigen from Friendship as sa "
//                + "WHERE sa.accepted IS NULL AND sa.usuariosByDestino.id=%d",
//                user.getId());
//        Query q = s.createQuery(sttm);
//
//        ArrayList<User> res = (ArrayList<User>) q.list();
//
//        s.getTransaction().commit();
//        HibernateUtil.getSessionFactory().close();
//        return res;
//    }

//
//    public boolean sendFriendRequest(User source, long dest_id) {
//        return sendFriendRequest(source, this.findById(dest_id));
//    }
//    public boolean sendFriendRequest(User source, User dest) {
//        if (dest == null || source == null) {
//            return false;
//        }
//        boolean res = false;
//
//        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
//        s.beginTransaction();
//        source = (User) s.get(User.class, source.getId());
//
//
//        if (source != null) {
//            Friendship sa = new Friendship();
//            sa.setUsersByDestino(dest);
//            sa.setUsersByOrigen(source);
//            sa.setAccepted(false);
//            s.save(sa);
//            res = true;
//
//        } else {
//            System.out.println("---------------------- alguno es Null :(");
//        }
//        s.getTransaction().commit();
//        HibernateUtil.getSessionFactory().close();
//
//        return res;
//    }
//    public boolean acceptFriendRequest(long source_id, long dest_id) {
//        return acceptFriendRequest(findById(source_id), findById(dest_id));
//    }
//    public boolean acceptFriendRequest(User source, User dest) {
//        if (dest == null || source == null) {
//            return false;
//        }
//        if (areFriends(source, dest)) {
//            return true;
//        }
//
//        boolean result = false;
//        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
//        s.beginTransaction();
//
//        dest = (User) s.get(User.class, dest.getId());
//        source = (User) s.get(User.class, source.getId());
//
//        Query q = s.createQuery("FROM Friendship WHERE origen=:source AND destino=:dest").
//                setLong("source", source.getId()).
//                setLong("dest", dest.getId());
//
//        Friendship res = (Friendship) q.uniqueResult();
//        if (res != null) {
//            /* -> */
//            res.setAccepted(Boolean.TRUE);
//            s.update(res);
//
//            /* <- */
//            Friendship sa = new Friendship();
//            sa.setUsersByOrigen(dest);
//            sa.setUsersByDestino(source);
//            sa.setAccepted(Boolean.TRUE);
//            s.save(sa);
//
//            result = true;
//
//        }
//
//        s.getTransaction().commit();
//        HibernateUtil.getSessionFactory().close();
//        return result;
//
//    }
//    public boolean commentOnWall(long source_id, long dest_id, String comment) {
//        if (comment == null || comment.equals("")) {
//            System.out.println("Are u kidding me?");
//            return false;
//        }
//
//        boolean res = false;
//        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
//        s.beginTransaction();
//
//        User source = (User) s.get(User.class, source_id);
//        User dest = (User) s.get(User.class, dest_id);
//
//        ComentariosMuro cm = new ComentariosMuro();
//        cm.setCuerpo(comment);
//        cm.setFecha(new Date());
//        cm.setUsersByDestino(dest);
//        cm.setUsersByOrigen(source);
//        s.save(cm);
//
//        s.getTransaction().commit();
//        HibernateUtil.getSessionFactory().close();
//
//        res = true;
//        return res;
//    }    
}
