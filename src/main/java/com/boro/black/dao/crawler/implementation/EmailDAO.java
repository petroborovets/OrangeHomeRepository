package com.boro.black.dao.crawler.implementation;

import com.boro.black.dao.implementation.ElementDAOImpl;
import com.boro.black.entity.crawler.Email;
import com.boro.black.exception.NonUniqueElementException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by petroborovets on 4/28/15.
 */
@Repository("emailDAO")
public class EmailDAO extends ElementDAOImpl<Email> {

    static Logger log = Logger.getLogger(EmailDAO.class.getName());

    public EmailDAO() {
        super(Email.class);
    }

    public Email checkForUnique(Email element, Session session) throws NonUniqueElementException {
        log.info("Checking email for uniqueness "+ element.getEmail());

        Query query = session.createQuery("from Email where email=:emailParameter");
        query.setParameter("emailParameter", element.getEmail());
        List<Email> emailList = query.list();
        if (emailList.size() == 0) {
            return element;
        } else {
            String errorMsg = "Email already exists." + element.getEmail();
            log.error(errorMsg);
            throw new NonUniqueElementException(errorMsg);
        }
    }
}
