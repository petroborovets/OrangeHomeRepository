package com.boro.orange.dao;

import com.boro.orange.entity.User;
import com.boro.orange.exception.NonUniqueElementException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by petroborovets on 4/5/15.
 */
@Repository("userDAO")
public class UserDAO extends ElementDAOImpl<User> {

    public UserDAO() {
        super(User.class);
    }

    public User checkForUnique(User element, Session session) throws NonUniqueElementException {
        return null;
    }
}
