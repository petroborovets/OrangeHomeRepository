package com.boro.black.component;

import com.boro.black.entity.crawler.Company;
import com.boro.black.entity.crawler.Email;
import com.boro.black.entity.crawler.SpiderTask;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.crawler.EmailService;
import com.boro.black.service.crawler.implementation.EmailServiceImpl;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by petroborovets on 10/25/15.
 */
public class Crawler extends WebCrawler {

    public static final String taskMapKey = "task";
    public static final String emailServiceMapKey = "emailService";
    public static final String companyMapKey = "company";
    private static final Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g"
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf"
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    // Crawler data storage
    private Company company;
    private EmailService emailService;
    private SpiderTask task;
    private CrawlerUtil crawlerUtil;

    @Override
    public void onStart() {
        super.onStart();

        Object customData = getMyController().getCustomData();
        if (customData instanceof HashMap) {
            HashMap initDataMap = (HashMap) customData;
            task = (SpiderTask) initDataMap.get(taskMapKey);
            emailService = (EmailService) initDataMap.get(emailServiceMapKey);
            company = (Company) initDataMap.get(companyMapKey);
        }
    }

    @Override
    public boolean shouldVisit(WebURL url) {
        // TODO Auto-generated method stub
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches();
    }

    @Override
    public SpiderTask getMyLocalData() {
        return task;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        String domainUrl = null;
        if (crawlerUtil == null) {
            crawlerUtil = new CrawlerUtil();
        }
        try {
            domainUrl = crawlerUtil.getDomainFromUrl(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (domainUrl == null || !company.getDomainUrl().equals(domainUrl)) {
            return;
        }

        System.out.printf("Spider #" + getMyId() + " crawling [" + url + "].");

        // crawl emails on page
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String pageText = htmlParseData.getText();
            //String html = htmlParseData.getHtml();
            String title = htmlParseData.getTitle();

            System.out.println("Title:" + title);
            ArrayList<Email> emailsOnPage = EmailServiceImpl.getEmailsFromText(pageText, company, url);
            System.out.println("Spider #" + getMyId() + " found [" + emailsOnPage.size() + "] email(s).");

            if (!emailsOnPage.isEmpty()) {
                try {
                    emailService.addAllElements(emailsOnPage);
                } catch (NonUniqueElementException e) {
                    System.out.println("Error, failed adding element list");
                    e.printStackTrace();
                }
            }


        }
    }


}
