package com.boro.black.component.crawler;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import edu.uci.ics.crawler4j.crawler.Configurable;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.frontier.DocIDServer;
import edu.uci.ics.crawler4j.frontier.Frontier;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.util.IO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petroborovets on 8/31/15.
 */
@Component
public class CrawlController extends Configurable {

    private static final Logger log = Logger.getLogger(CrawlController.class.getName());

    /**
     * The 'customData' object can be used for passing custom crawl-related
     * configurations to different components of the crawler.
     */
    protected Object customData;

    /**
     * Once the crawling session finishes the controller collects the local data
     * of the crawler threads and stores them in this List.
     */
    protected List<Object> crawlersLocalData = new ArrayList<Object>();

    /**
     * Is the crawling of this session finished?
     */
    protected boolean finished;

    /**
     * Is the crawling session set to 'shutdown'. Crawler threads monitor this
     * flag and when it is set they will no longer process new pages.
     */
    protected boolean shuttingDown;

    protected PageFetcher pageFetcher;
    protected RobotstxtServer robotstxtServer;
    protected Frontier frontier;
    protected DocIDServer docIdServer;

    protected final Object waitingLock = new Object();

    public CrawlController(CrawlConfig config, PageFetcher pageFetcher, RobotstxtServer robotstxtServer)
            throws Exception {
        super(config);

        config.validate();

        // Creating folder if not exist
        File folder = new File(config.getCrawlStorageFolder());
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new Exception("Couldn't create this folder: " + folder.getAbsolutePath());
            }
        }

        boolean resumable = config.isResumableCrawling();

        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        envConfig.setTransactional(resumable);
        envConfig.setLocking(resumable);

        File envHome = new File(config.getCrawlStorageFolder() + "/frontier");
        if (!envHome.exists()) {
            if (!envHome.mkdir()) {
                throw new Exception("Couldn't create this folder: " + envHome.getAbsolutePath());
            }
        }
        if (!resumable) {
            IO.deleteFolderContents(envHome);
        }

        Environment env = new Environment(envHome, envConfig);
        docIdServer = new DocIDServer(env, config);
        frontier = new Frontier(env, config, docIdServer);

        this.pageFetcher = pageFetcher;
        this.robotstxtServer = robotstxtServer;

        finished = false;
        shuttingDown = false;
    }


}
