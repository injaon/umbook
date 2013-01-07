/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import exceptions.FriendshipException;
import java.util.List;
import model.Friendship;
import model.HibernateUtil;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author goks
 */
public class UserService extends ABM<User> {

    public boolean areFriends(User source, User dest) throws FriendshipException {
        if (source.getId().equals(dest.getId())) {
            throw new FriendshipException("It's the same user.");
        }

        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

        String sttm = String.format(
                "FROM Friendship WHERE status='accepted' AND "
                + " origin = %d AND destiny = %d",
                source.getId(), dest.getId());

        Query q = s.createQuery(sttm.toString());
        Friendship friendship = (Friendship) q.uniqueResult();
        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();

        return friendship != null ? true : false;
    }

    public void sendFriendRequest(User source, User destiny) throws FriendshipException {
        if (source.getId().equals(destiny.getId())) {
            throw new FriendshipException("It's the same user.");
        }
        if (this.areFriends(source, destiny)) {
            throw new FriendshipException("They are already friends");
        }


    }

    public static void main(String[] args) {

        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();

//        String sttm = String.format(
        String sttm = "FROM User";
                

        Query q = s.createQuery(sttm);
        List<User> list = q.list();
        
        
        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();

    }
}
