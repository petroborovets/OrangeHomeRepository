package com.boro.black.dao.implementation;

import com.boro.black.dao.ElementDAO;
import com.boro.black.exception.NonUniqueElementException;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by petroborovets on 4/8/15.
 */
public abstract class ElementDAOImpl<E> implements ElementDAO<E> {
    private Class<E> elementClass;
    @Autowired
    protected SessionFactory sessionFactory;

    static Logger log = Logger.getLogger(ElementDAOImpl.class.getName());


    public ElementDAOImpl(Class<E> elementClass) {
        this.elementClass = elementClass;
    }

    public void addElement(E element) throws NonUniqueElementException {
        Session session = sessionFactory.getCurrentSession();
        session.save(checkForUnique(element, session));
    }

    public void addAllElements(List<E> elements) throws NonUniqueElementException {
        Session session = sessionFactory.getCurrentSession();
        for (E element : elements) {
            try {
                checkForUnique(element, session);
            } catch (NonUniqueElementException e) {
                log.error("Element ["+ element+"] is not unique.");
                continue;
            }
            session.save(element);
        }
        session.close();
    }

    public void updateElement(E element) {
        Session session = sessionFactory.getCurrentSession();
        session.update(element);
    }

    @SuppressWarnings("unchecked")
    public List<E> getAllElements() {
        return sessionFactory.getCurrentSession().createCriteria(elementClass)
                .list();
    }

    public void deleteElement(E element) {
        sessionFactory.getCurrentSession().delete(element);
    }

    public List<E> getElementsByCriteria(Object... criteria) {
        return null;
    }

    @SuppressWarnings("unchecked")
    public E getElementByID(Long elementId) {
        return (E) sessionFactory.getCurrentSession().get(elementClass,
                elementId);
    }

    public E getElementByQuery(String quety) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(quety).addEntity(elementClass);
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (E) query.list().get(0);
    }
}
