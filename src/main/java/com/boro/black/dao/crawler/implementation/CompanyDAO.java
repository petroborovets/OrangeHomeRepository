package com.boro.black.dao.crawler.implementation;

import com.boro.black.dao.implementation.ElementDAOImpl;
import com.boro.black.entity.crawler.Company;
import com.boro.black.entity.crawler.Email;
import com.boro.black.exception.NonUniqueElementException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by petroborovets on 6/28/15.
 */
@Repository("companyDAO")
public class CompanyDAO extends ElementDAOImpl<Company> {
    static Logger log = Logger.getLogger(CompanyDAO.class.getName());

    public CompanyDAO() {
        super(Company.class);
    }

    public Company checkForUnique(Company element, Session session) throws NonUniqueElementException {
        String companyDomainURL = element.getDomainUrl();

        Query query = session.createQuery("from Company where domainUrl=:companyDomainURL");
        query.setParameter("companyDomainURL", companyDomainURL);

        List<Company> companyList = query.list();
        if (companyList.size() == 0) {
            return element;
        } else {
            String errorMsg = "Email already exists." + element.getDomainUrl();
            log.error(errorMsg);
            throw new NonUniqueElementException(errorMsg);
        }
    }

    public List<Email> getCompanyEmails(Long companyPk) {
        log.info("Get company emails using company PK:[" + companyPk + "]");

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Email where company.id=:companyPk");
        query.setParameter("companyPk", companyPk);

        List<Email> companyEmails = query.list();
        if (companyEmails == null) {
            log.error("Failed to get companyEmails using company pk:[" + companyPk + "]");
        } else if(companyEmails.size() == 0) {
            log.warn("No emails found for company with pk:[" + companyPk + "]");
        }

        return companyEmails;
    }
}
