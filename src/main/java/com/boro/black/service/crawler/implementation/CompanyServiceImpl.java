package com.boro.black.service.crawler.implementation;

import com.boro.black.dao.ElementDAO;
import com.boro.black.dao.crawler.implementation.CompanyDAO;
import com.boro.black.entity.crawler.Company;
import com.boro.black.entity.crawler.Email;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.crawler.CompanyService;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by petroborovets on 6/28/15.
 */
@Transactional
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    static Logger log = Logger.getLogger(EmailServiceImpl.class.getName());

    @Autowired
    private ElementDAO<Company> companyDAO;

    public void addElement(Company element) throws NonUniqueElementException {
        companyDAO.addElement(element);
    }

    public void updateElement(Company element) throws NonUniqueElementException {
        companyDAO.updateElement(element);
    }

    public List<Company> getAllElements() {
        return companyDAO.getAllElements();
    }

    public void deleteElement(Company element) {
        companyDAO.deleteElement(element);
    }

    public List<Company> getElementsByCriteria(Object... criteria) {
        return null;
    }

    public Company getElementByID(Long elementId) {
        return companyDAO.getElementByID(elementId);
    }

    public List<Email> getCompanyEmails(Long companyPk) {
        log.info("Get company with pk [" + companyPk + " emails.");
        if(companyDAO instanceof CompanyDAO) {
            CompanyDAO companyDAOImpl = (CompanyDAO) companyDAO;
            companyDAOImpl.getCompanyEmails(companyPk);
        }
        log.error("Failed to get company emails.");
        return null;
    }
}
