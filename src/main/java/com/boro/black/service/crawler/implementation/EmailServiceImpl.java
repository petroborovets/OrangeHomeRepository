package com.boro.black.service.crawler.implementation;

import com.boro.black.dao.ElementDAO;
import com.boro.black.entity.crawler.Company;
import com.boro.black.entity.crawler.Email;
import com.boro.black.entity.crawler.EmailContext;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.crawler.EmailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void addAllElements(List<Email> elements) throws NonUniqueElementException {
        emailDAO.addAllElements(elements);
    }

    public void updateElement(Email email) {
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

    public static ArrayList<Email> getEmailsFromText(String pageText, Company company, String url) {
        ArrayList<Email> emails = new ArrayList<Email>();
        Pattern pattern;
        Matcher matcher;

        //get email strings from page
        pageText = pageText.replaceAll("\\[at\\]", "@");
        pageText = pageText.replaceAll("\\(at\\)", "@");

        pattern = Pattern.compile("\\b[A-Z0-9._%+-]+@+[A-Z0-9.-]+\\.[A-Z&&[^png]]{2,4}\\b",
                Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(pageText);

        while (matcher.find()) {
            Email email = new Email();
            email.setEmail(matcher.group());
            email.setUrl(url);
            email.setCompany(company);
            emails.add(email);
        }

        // get email contexts
        for (Email emailEntity : emails) {
            String leftRegEx = "([\\w\\s,.\\u0000-\\uFFFF:]{" + 200 + "})";
            String rightRegEx = "([\\w\\s,.\\u0000-\\uFFFF:]{" + 100 + "})";

            String email = emailEntity.getEmail();
            email = email.replace(".", "\\Q.\\E");
            String contextRe = leftRegEx + email + rightRegEx;

            pageText = pageText.replaceAll("\\n", "");
            pageText = pageText.replaceAll("\\t", "");

            pattern = Pattern.compile(contextRe);
            matcher = pattern.matcher(pageText);

            while (matcher.find()) {
                EmailContext emailContext = new EmailContext();
                emailContext.setLeftContext(matcher.group(1));
                emailContext.setRightContext(matcher.group(2));

                emailEntity.setEmailContext(emailContext);
            }
        }

        return emails;
    }

    /**
     * Filter grouped emails
     * @param groupedEmails list of not unique emails
     * @return List of unique emails
     */
    public List<Email> filterEmails(List<Email> groupedEmails) {
        List<Email> uniqueEmails = new ArrayList<Email>();

        boolean haveToAddToUnique;
        for (Email email : groupedEmails) {
            if (uniqueEmails.isEmpty()) {
                uniqueEmails.add(email);
                continue;
            }
            haveToAddToUnique = true;
            for (Email uniqueEmail : uniqueEmails) {
                if (uniqueEmail.getEmail().equals(email.getEmail())) {
                    //TODO merge emails if needed
                    haveToAddToUnique = false;
                    break;
                }
            }
            if (haveToAddToUnique) {
                uniqueEmails.add(email);
            }
        }

        return uniqueEmails;
    }
}
