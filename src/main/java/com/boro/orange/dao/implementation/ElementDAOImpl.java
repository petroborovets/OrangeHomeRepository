package com.boro.orange.dao.implementation;

import com.boro.orange.dao.ElementDAO;
import com.boro.orange.exception.NonUniqueElementException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by petroborovets on 4/8/15.
 */
public abstract class ElementDAOImpl<E> implements ElementDAO<E> {
    private Class<E> elementClass;
    @Autowired
    protected SessionFactory sessionFactory;

    public ElementDAOImpl(Class<E> elementClass) {
        this.elementClass = elementClass;
    }

    public void addElement(E element) throws NonUniqueElementException {
        Session session = sessionFactory.getCurrentSession();
        checkForUnique(element, session);
        session.save(element);
    }

    public void updateElement(E element) throws NonUniqueElementException {
        Session session = sessionFactory.getCurrentSession();
        E e = checkForUnique(element, session);
        session.update(e);
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
}
