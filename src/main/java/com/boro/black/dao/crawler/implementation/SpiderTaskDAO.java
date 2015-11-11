package com.boro.black.dao.crawler.implementation;

import com.boro.black.dao.implementation.ElementDAOImpl;
import com.boro.black.entity.crawler.SpiderTask;
import com.boro.black.exception.NonUniqueElementException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by petroborovets on 7/24/15.
 */
@Repository("spiderTaskDAO")
public class SpiderTaskDAO extends ElementDAOImpl<SpiderTask> {
    static Logger log = Logger.getLogger(SpiderTaskDAO.class.getName());

    public SpiderTaskDAO() {
        super(SpiderTask.class);
    }

    public SpiderTask checkForUnique(SpiderTask element, Session session) throws NonUniqueElementException {
        return element;
    }
}
