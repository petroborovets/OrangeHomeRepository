package com.boro.black.service;

import com.boro.black.exception.NonUniqueElementException;

import java.util.List;

/**
 * Created by petroborovets on 4/28/15.
 */
public interface ElementService<E> {
    void addElement(E element) throws NonUniqueElementException;

    void updateElement(E element) throws NonUniqueElementException;

    List<E> getAllElements();

    void deleteElement(E element);

    List<E> getElementsByCriteria(Object... criteria);

    E getElementByID(Long elementId);
}
