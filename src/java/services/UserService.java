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

package services;

import exceptions.FriendshipException;
import form.SignUp;
import java.text.ParseException;
import model.Comment;
import model.Friendship;
import model.HibernateUtil;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;

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

    public User fromForm(SignUp form) {
        User user = new User();
        user.setName(form.getUser());
        user.setFirstName(form.getNombre());
        user.setLastName(form.getApellido());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setGender(form.getGenero());
        try {
            user.setBirth(new DateUtil().parse(form.getBirth()));
        } catch (ParseException ex) {
            user.setBirth(null);
        }
        return user;
    }

    @Override
    public void add(User obj) {
        if (obj.getPhoto() == null || obj.getPhoto().equals("")) {
            if (obj.getGender().equals("male")) {
                obj.setPhoto("/img/user_male.png");
            } else {
                obj.setPhoto("/img/user_female.png");
            }
        }
        super.add(obj);
    }

    public void initComments(User user) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
    
        s.refresh(user);
        user.getCommentsOnWall();

        for (Comment c : user.getCommentsOnWall()) {
            c.getOrigin();
        }

        s.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }
}
