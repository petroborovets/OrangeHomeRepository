package com.boro.black.service.crawler.implementation;

import com.boro.black.dao.ElementDAO;
import com.boro.black.entity.crawler.Email;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.crawler.EmailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by petroborovets on 4/28/15.
 */
@Transactional
@Service("emailService")
public class EmailServiceImpl implements EmailService {

    static Logger log = Logger.getLogger(EmailServiceImpl.class.getName());

    @Autowired
    private ElementDAO<Email> emailDAO;

    public void addElement(Email email) throws NonUniqueElementException {
        emailDAO.addElement(email);
    }

    public void updateElement(Email email) throws NonUniqueElementException {
        emailDAO.updateElement(email);
    }

    public List<Email> getAllElements() {
        return emailDAO.getAllElements();
    }

    public void deleteElement(Email email) {
        emailDAO.deleteElement(email);
    }
    //TODO implement
    public List<Email> getElementsByCriteria(Object... criteria) {
        return null;
    }

    public Email getElementByID(Long emailId) {
        return emailDAO.getElementByID(emailId);
    }


}
