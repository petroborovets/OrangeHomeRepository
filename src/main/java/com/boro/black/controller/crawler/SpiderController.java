package com.boro.black.controller.crawler;

import com.boro.black.component.Crawler;
import com.boro.black.component.CrawlerUtil;
import com.boro.black.component.ModelUtil;
import com.boro.black.component.validation.GeneralValidation;
import com.boro.black.dto.SpiderTaskDTO;
import com.boro.black.entity.crawler.Company;
import com.boro.black.entity.crawler.SpiderTask;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.UserService;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/spider", method = RequestMethod.GET)
    public String home(ModelMap model) {
        log.info("Loading spider.jsp");
        modelUtil.warp(model);

        return "spider";
    }

    @RequestMapping(value = "/spiderByURL", method = RequestMethod.POST)
    public String startSpiderUsingUrl(@RequestParam("url") String url,
                                      @RequestParam("taskName") String taskName,
                                      @RequestParam("taskDescription") String taskDescription,
                                      @RequestParam("crawlingTime") String crawlingTimeSting,
                                      ModelMap model) {
        log.info("Starting spider using url: [" + url + "] for [" + crawlingTimeSting + "] minutes.");
        modelUtil.warp(model);


        int crawlingTime = Integer.parseInt(crawlingTimeSting);
        if (!GeneralValidation.validateURL(url)) {
            log.error("URL is not valid.");
            model.addAttribute("errorMessage", "URL is not valid.");
        }

        // Configuring crawler
        String crawlStorageFolder = "/Users/petroborovets/Desktop/Crawl";
        int numberOfCrawlers = 4;
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        //Starting crawler
        try {
            // Creating spider task
            SpiderTask spiderTask = new SpiderTask();
            spiderTask.setUrl(url);
            spiderTask.setName(taskName);
            spiderTask.setDescription(taskDescription);
            spiderTask.setUser(userService.getLoggedInUser());

            spiderTaskService.addElement(spiderTask);
            // Creating company
            Company company = new Company();
            List<SpiderTask> spiderTasks = new ArrayList<SpiderTask>();
            spiderTasks.add(spiderTask);
            company.setSpiderTasks(spiderTasks);
            company.setDomainUrl(crawlerUtil.getDomainFromUrl(url));
            try {
                if (companyService.checkForUnique(company) != null)
                    companyService.addElement(company);
            } catch (NonUniqueElementException e) {
                company = companyService.getCompanyByDomain(company.getDomainUrl());
            }

            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

            Map<String, Object> crawlerInitDataMap = new HashMap<String, Object>();
            crawlerInitDataMap.put(Crawler.taskMapKey, spiderTask);
            crawlerInitDataMap.put(Crawler.companyMapKey, company);
            crawlerInitDataMap.put(Crawler.emailServiceMapKey, emailService);
            crawlerInitDataMap.put(Crawler.crawlingTimeKey, crawlingTime);

            controller.setCustomData(crawlerInitDataMap);
            controller.addSeed(url);
            controller.start(Crawler.class, numberOfCrawlers);

            if (controller.isFinished()) {
                spiderTask.setProgress(SpiderTask.IS_FINISHED);
                spiderTaskService.updateElement(spiderTask);
            }

            // Get data collected if needed
            /*
            List<Object> companies = controller.getCrawlersLocalData();
            */
            log.info("Finished startSpiderUsingUrl()");

        } catch (Exception e) {
            log.error("Failed to start crawler: " + e);
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getLocalizedMessage());
        }

        return "spider";
    }

    @RequestMapping(value = "/spider/{id}", method = RequestMethod.GET)
    public String getSpiderDetails(@PathVariable(value = "id") Long id, ModelMap model) {
        log.info("Loading SpiderDetails.jsp");
        modelUtil.warp(model);

        SpiderTask spiderTask = spiderTaskService.getElementByID(id);
        model.addAttribute("spiderTaskDTO", spiderTaskService.getDto(spiderTask));

        return "spiderDetails";
    }

    @RequestMapping(value = "/spiderTasks", method = RequestMethod.GET)
    public String getSpiderTasks(ModelMap model) {
        log.info("Loading spiderTasks.jsp");
        modelUtil.warp(model);

        int companiesSavedCount = companyService.getAllElements().size();
        int emailsSavedCount = emailService.getAllElements().size();
        int numberOfTasks = spiderTaskService.getAllElements().size();

        model.addAttribute("companiesSavedCount", companiesSavedCount);
        model.addAttribute("emailsSavedCount", emailsSavedCount);
        model.addAttribute("tasksCount", numberOfTasks);

        return "spiderTasks";
    }

    @RequestMapping(value = "/spider/info", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<SpiderTaskDTO> getJsonTableData(ModelMap model) {
        return spiderTaskService.getDtoList(spiderTaskService.getAllElements());
    }
}
