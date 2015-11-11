package com.boro.black.service.crawler;

import com.boro.black.entity.crawler.Company;
import com.boro.black.entity.crawler.Email;
import com.boro.black.service.ElementService;

import java.util.List;

/**
 * Created by petroborovets on 6/28/15.
 */
public interface CompanyService extends ElementService<Company> {
    public List<Email> getCompanyEmails(Long companyPk);
    List<Company> filterCompanies(List<Company> companies);
    public Company getCompanyByDomain(String domain);
}
