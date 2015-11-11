package com.boro.black.dao;

import com.boro.black.exception.NonUniqueElementException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by petroborovets on 4/8/15.
 */
public interface ElementDAO<E> {

    void addElement(E element) throws NonUniqueElementException;

    void addAllElements(List<E> elements) throws NonUniqueElementException;

    void updateElement(E element);

    List<E> getAllElements();

    void deleteElement(E element);

    List<E> getElementsByCriteria(Object... criteria);

    E getElementByID(Long elementId);

    E checkForUnique(E element, Session session) throws NonUniqueElementException;

    E getElementByQuery(String quety);
}
