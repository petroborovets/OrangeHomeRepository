package com.boro.orange.dao.implementation;

import com.boro.orange.dao.implementation.ElementDAOImpl;
import com.boro.orange.entity.User;
import com.boro.orange.exception.NonUniqueElementException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by petroborovets on 4/5/15.
 */
@Repository("userDAO")
public class UserDAO extends ElementDAOImpl<User> {

    @Autowired
    protected SessionFactory sessionFactory;

    public UserDAO() {
        super(User.class);
    }

    public User checkForUnique(User element, Session session) throws NonUniqueElementException {
        List<User> userList = getAllElements();
        for (User user : userList) {
            if (user.getEmail().equals(element.getEmail()))
                throw new NonUniqueElementException("User with email [" + element.getEmail()
                        + "] already exist.");
        }
        return element;
    }
}
