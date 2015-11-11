package com.boro.black.controller.crawler;

import com.boro.black.component.Crawler;
import com.boro.black.component.CrawlerUtil;
import com.boro.black.component.ModelUtil;
import com.boro.black.component.validation.GeneralValidation;
import com.boro.black.dto.SpiderTaskDTO;
import com.boro.black.entity.crawler.Company;
import com.boro.black.entity.crawler.SpiderTask;
import com.boro.black.service.crawler.CompanyService;
import com.boro.black.service.crawler.EmailService;
import com.boro.black.service.crawler.SpiderTaskService;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by petroborovets on 7/15/15.
 */
@Controller
@RequestMapping("/")
public class SpiderController {
    static Logger log = Logger.getLogger(SpiderController.class.getName());

    @Autowired
    ModelUtil modelUtil;
    @Autowired
    SpiderTaskService spiderTaskService;
    @Autowired
    CompanyService companyService;
    @Autowired
    EmailService emailService;
    @Autowired
    CrawlerUtil crawlerUtil;

    @RequestMapping(value = "/spider", method = RequestMethod.GET)
    public String home(ModelMap model) {
        log.info("Loading spider.jsp");
        modelUtil.warp(model);

        return "spider";
    }

    @RequestMapping(value = "/spiderByURL", method = RequestMethod.POST)
    public String startSpiderUsingUrl(@RequestParam("url") String url, @RequestParam("crawlingTime") String crawlingTimeSting, ModelMap model) {
        log.info("Starting spider using url: [" + url + "] for [" + crawlingTimeSting + "] minutes.");
        modelUtil.warp(model);

        int crawlingTime = Integer.parseInt(crawlingTimeSting);
        if (!GeneralValidation.validateURL(url)) {
            log.error("URL is not valid.");
        }

        // Configuring crawler
        String crawlStorageFolder = "/Users/petroborovets/Desktop/Crawl";
        int numberOfCrawlers = 4;
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxPagesToFetch(200);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        //Starting crawler
        try {
            // Creating spider task
            SpiderTask spiderTask = new SpiderTask();
            spiderTask.setName("Spider url [" + url + "]");
            spiderTask.setDescription("Spider url [" + url + "]");
            spiderTaskService.addElement(spiderTask);
            // Creating company
            Company company = new Company();
            company.setSpiderTask(spiderTask);
            company.setDomainUrl(crawlerUtil.getDomainFromUrl(url));
            companyService.addElement(company);

            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

            Map<String, Object> crawlerInitDataMap = new HashMap<String, Object>();
            crawlerInitDataMap.put(Crawler.taskMapKey, spiderTask);
            crawlerInitDataMap.put(Crawler.companyMapKey, company);
            crawlerInitDataMap.put(Crawler.emailServiceMapKey, emailService);

            controller.setCustomData(crawlerInitDataMap);
            controller.addSeed(url);
            controller.start(Crawler.class, numberOfCrawlers);

            if (controller.isFinished()) {
                spiderTask.setFinishDate();
                spiderTaskService.updateElement(spiderTask);
            }

            // Get data collected if needed
            /*
            List<Object> companies = controller.getCrawlersLocalData();
            */

        } catch (Exception e) {
            log.error("Failed to start crawler: " + e);
            e.printStackTrace();
        }

        return "spider";
    }

    @RequestMapping(value = "/spider/info", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<SpiderTaskDTO> getJsonTableData(ModelMap model) {

        return spiderTaskService.getDtoList(spiderTaskService.getAllElements());
    }
}
