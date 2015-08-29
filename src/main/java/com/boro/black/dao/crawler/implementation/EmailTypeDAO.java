package com.boro.black.dao.crawler.implementation;

import com.boro.black.dao.implementation.ElementDAOImpl;
import com.boro.black.entity.crawler.EmailType;
import com.boro.black.exception.NonUniqueElementException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by petroborovets on 4/27/15.
 */
@Repository("EmailTypeDAO")
public class EmailTypeDAO extends ElementDAOImpl<EmailType> {

    public EmailTypeDAO() {
        super(EmailType.class);
    }

    public EmailType checkForUnique(EmailType element, Session session) throws NonUniqueElementException {
        return null;
    }
}
