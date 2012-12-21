/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import model.HibernateUtil;
import model.User;
import org.hibernate.Session;

/**
 *
 * @author goks
 */
public abstract class ABM<T> {

    public void add(T obj) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.save(obj);
        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    public void delete(T obj) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        s.delete(obj);
        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    public void update(T obj) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
//        User dbuser = (T) s.get(User.class, user.getId());
        s.merge(obj);
        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }
}
