package com.boro.black.service.crawler.implementation;

import com.boro.black.dao.ElementDAO;
import com.boro.black.dao.crawler.implementation.CompanyDAO;
import com.boro.black.entity.crawler.Company;
import com.boro.black.entity.crawler.Email;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.crawler.CompanyService;
import com.boro.black.service.crawler.EmailService;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petroborovets on 6/28/15.
 */
@Transactional
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    static Logger log = Logger.getLogger(EmailServiceImpl.class.getName());

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private EmailService emailService;

    public void addElement(Company element) throws NonUniqueElementException {
        companyDAO.addElement(element);
    }

    public void addAllElements(List<Company> element) throws NonUniqueElementException {
        companyDAO.addAllElements(element);
    }

    public void updateElement(Company element) {
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

    public Company checkForUnique(Company company) throws NonUniqueElementException {
        return companyDAO.checkForUnique(company, companyDAO.getSession());
    }

    public Company getElementByID(Long elementId) {
        return companyDAO.getElementByID(elementId);
    }

    public Company getCompanyByDomain(String domain) {
        log.info("Get company with by domainUrl [" + domain + "].");
        return companyDAO.getCompanyByDomainUrl(domain);
    }

    public List<Email> getCompanyEmails(Long companyPk) {
        log.info("Get company with pk [" + companyPk + "] emails.");
        return companyDAO.getCompanyEmails(companyPk);
    }
    
    public List<Company> getCompaniesByTask(Long taskId) {
        log.info("Get companies with task pk [" + taskId + "]");
        return companyDAO.getCompaniesByTaskId(taskId);
    }

    public List<Company> filterCompanies(List<Company> companies) {
        List<Company> filteredCompanies = new ArrayList<Company>();

        boolean haveToAddToUnique;

        List<Company> companiesToDelete = new ArrayList<Company>();

        //check if list contails null elements (sometimes crawler fills with null)
        for (Company company : companies) {
            if (company.getDomainUrl() == null && company.getEmails() == null) {
                companiesToDelete.add(company);
            }
        }
        companies.removeAll(companiesToDelete);

        for (Company company : companies) {
            //add to unique if unique is empty
            if (filteredCompanies.isEmpty()) {
                if (company.getEmails() != null)
                    filteredCompanies.add(company);
                continue;
            }
            //if not empty,
            // 1: if same company exists in unique - group, filter emails and add to existing unique company
            // 2: if same company doesn't exist in unique - add company
            haveToAddToUnique = true;
            for (Company uniqueCompany : filteredCompanies) {
                if (uniqueCompany.getDomainUrl().equals(company.getDomainUrl())) {
                    List<Email> emailsGrouped = new ArrayList<Email>();

                    emailsGrouped.addAll(uniqueCompany.getEmails());
                    emailsGrouped.addAll(company.getEmails());

                    List<Email> uniqueCompanyEmails =
                            emailService.filterEmails(emailsGrouped);

                    uniqueCompany.setEmails(uniqueCompanyEmails);
                    haveToAddToUnique = false;
                    break;
                }
            }
            // 2:
            if (haveToAddToUnique) {
                filteredCompanies.add(company);
            }
        }

        return filteredCompanies;
    }

}
