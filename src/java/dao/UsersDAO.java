package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.HibernateUtil;
import model.Friendship;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;

/*
 * @author goks
 */
public class UsersDAO {

    private User user;

    public UsersDAO() {
        this.user = null;
    }

    public UsersDAO(User user) {
        this.user = user;
    }

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

        String sttm = String.format("from User as u where u.user='%s'",
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

    public ArrayList<User> find(String username, String email,
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
            sttm.append(String.format("LOWER(u.nombre) LIKE '%%%s%%' OR ",
                    realName));
        }
        if (lastName != null) {
            sttm.append(String.format("LOWER(u.apellido) LIKE '%%%s%%' OR ",
                    lastName));
        }
        if (gender != null) {
            sttm.append(String.format("u.genero = %b OR ",
                    gender));
        }
        sttm.append("false=true)");

        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

        Query q = s.createQuery(sttm.toString());
        //        q.setFirstResult(first);
        //        q.setMaxResults(max);
        ArrayList<User> res = (ArrayList<User>) q.list();

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return res;

    }

        public List<User> getFriendRequests() {
        if (user == null) {
            return null;
        }
        
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

        String sttm = String.format(
                "SELECT usuariosByOrigen from Friendship as sa "
                + "WHERE sa.accepted IS NULL AND sa.usuariosByDestino.id=%d",
                user.getId());
        Query q = s.createQuery(sttm);

        ArrayList<User> res = (ArrayList<User>) q.list();

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return res;
    }

    
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
    public static void main(String[] args) {
        UsersDAO dao = new UsersDAO();

        Session s = HibernateUtil.getSessionFactory().getCurrentSession();

//        User u = dao.findById(3L);
//        System.out.println(u.getLastName());

    }
}
