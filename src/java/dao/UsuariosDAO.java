package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.HibernateUtil;
import model.SolicitudAmistad;
import model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

/*
 * @author goks
 */
public class UsuariosDAO {

    private Usuario user;

    public UsuariosDAO() {
        this.user = null;
    }

    public UsuariosDAO(Usuario user) {
        this.user = user;
    }

    public void insert(Usuario user) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

        s.save(user);

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    public Usuario findById(long id) {
        Usuario res = null;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        /*
        
        String sttm = String.format("from Usuario as u where u.id=%d", id);
        Query q = s.createQuery(sttm);
        res = (Usuario) q.uniqueResult();
        
         * */
        res = (Usuario) s.get(Usuario.class, id);

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return res;
    }

    public Usuario findByUsername(String username) {
        Usuario res = null;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        String sttm = String.format("from Usuario as u where u.user='%s'",
                username);
        Query q = s.createQuery(sttm);
        res = (Usuario) q.uniqueResult();
        s.getTransaction().commit();

        /* close the session */
        HibernateUtil.getSessionFactory().close();
        return res;
    }

    public Usuario findByEmail(String email) {
        Usuario res = null;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        String sttm = String.format("from Usuario as u where u.email='%s'",
                email);
        Query q = s.createQuery(sttm);
        res = (Usuario) q.uniqueResult();
        s.getTransaction().commit();

        /* close the session */
        HibernateUtil.getSessionFactory().close();
        return res;
    }

    public Usuario findByUserAndPass(String user, String pass) {
        Usuario res = null;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        String sttm = String.format("from Usuario as u where u.user='%s'"
                + " and u.password='%s'", user, pass);
        Query q = s.createQuery(sttm);
        res = (Usuario) q.uniqueResult();

        s.getTransaction().commit();

        /* close the session */
        HibernateUtil.getSessionFactory().close();

        return res;
    }

    public ArrayList<Usuario> find(Usuario user, String username, String email,
            String realName, String lastName, String gender) {

        long uid = user.getId();
        StringBuilder sttm = new StringBuilder("FROM Usuario AS u WHERE ( ");
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
        ArrayList<Usuario> res = (ArrayList<Usuario>) q.list();

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();


        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

//    public boolean sendFriendRequest(long source_id, long dest_id) {
//        return sendFriendRequest(this.findById(source_id), this.findById(dest_id));
//    }
//
//    public boolean sendFriendRequest(Usuario source, long dest_id) {
//        return sendFriendRequest(source, this.findById(dest_id));
//    }

//    public boolean sendFriendRequest(Usuario source, Usuario dest) {
//        if (dest == null || source == null) {
//            return false;
//        }
//        boolean res = false;
//
//        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
//        s.beginTransaction();
//        source = (Usuario) s.get(Usuario.class, source.getId());
//
//
//        if (source != null) {
//            SolicitudAmistad sa = new SolicitudAmistad();
//            sa.setUsuariosByDestino(dest);
//            sa.setUsuariosByOrigen(source);
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

    public boolean areFriends(Usuario source, Usuario dest) {
        if (source == null || dest == null) {
            return false;
        }
        if (source.getId() == dest.getId()) {
            return true;
        }

        boolean res = false;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

        String sttm = String.format("FROM SolicitudAmistad WHERE accepted=true AND "
                + " origen = %d AND destino = %d ",
                source.getId(), dest.getId());

        Query q = s.createQuery(sttm.toString());

        SolicitudAmistad sa = (SolicitudAmistad) q.uniqueResult();
        if (sa != null) {
            res = true;
        }

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();

        return res;
    }

//    public boolean acceptFriendRequest(long source_id, long dest_id) {
//        return acceptFriendRequest(findById(source_id), findById(dest_id));
//    }

//    public boolean acceptFriendRequest(Usuario source, Usuario dest) {
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
//        dest = (Usuario) s.get(Usuario.class, dest.getId());
//        source = (Usuario) s.get(Usuario.class, source.getId());
//
//        Query q = s.createQuery("FROM SolicitudAmistad WHERE origen=:source AND destino=:dest").
//                setLong("source", source.getId()).
//                setLong("dest", dest.getId());
//
//        SolicitudAmistad res = (SolicitudAmistad) q.uniqueResult();
//        if (res != null) {
//            /* -> */
//            res.setAccepted(Boolean.TRUE);
//            s.update(res);
//
//            /* <- */
//            SolicitudAmistad sa = new SolicitudAmistad();
//            sa.setUsuariosByOrigen(dest);
//            sa.setUsuariosByDestino(source);
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

    public List<Usuario> getFriendRequests() {
        if (user == null) {
            return null;
        }

        ArrayList<Usuario> res = null;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

        String sttm = String.format(
                "SELECT usuariosByOrigen from SolicitudAmistad as sa "
                + "WHERE sa.accepted IS NULL AND sa.usuariosByDestino.id=%d",
                user.getId());
        Query q = s.createQuery(sttm);

        res = (ArrayList<Usuario>) q.list();

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return res;
    }

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
//        Usuario source = (Usuario) s.get(Usuario.class, source_id);
//        Usuario dest = (Usuario) s.get(Usuario.class, dest_id);
//
//        ComentariosMuro cm = new ComentariosMuro();
//        cm.setCuerpo(comment);
//        cm.setFecha(new Date());
//        cm.setUsuariosByDestino(dest);
//        cm.setUsuariosByOrigen(source);
//        s.save(cm);
//
//        s.getTransaction().commit();
//        HibernateUtil.getSessionFactory().close();
//
//        res = true;
//        return res;
//    }

    public void update(Usuario user) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

        Usuario dbuser = (Usuario) s.get(Usuario.class, user.getId());
        s.merge(user);
        
        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    public void delete(long id) {
    }

    public static void main(String[] args) {
        UsuariosDAO dao = new UsuariosDAO();
        Usuario u = dao.findById(3L);
        System.out.println(u.getApellido());
        
    }

}
