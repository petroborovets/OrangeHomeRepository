package com.boro.black.controller.crawler;

import com.boro.black.component.ModelUtil;
import com.boro.black.dto.GismeteoDataDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by petroborovets on 7/15/15.
 */
@Controller
@RequestMapping("/")
public class SpiderController {
    static Logger log = Logger.getLogger(SpiderController.class.getName());

    @Autowired
    ModelUtil modelUtil;

    @RequestMapping(value = "/spider", method = RequestMethod.GET)
    public String home(ModelMap model) {
        log.info("Loading spider.jsp");
        modelUtil.warp(model);

        return "spider";
    }

    @RequestMapping(value = "/spiderByURL", method = RequestMethod.POST)
    public String startSpiderUsingUrl(@RequestParam("url") String url, ModelMap model) {
        log.info("Loading spider.jsp");
        modelUtil.warp(model);

        //TODO start crawler using url

        return "spider";
    }

    @RequestMapping(value = "/spider/info", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<GismeteoDataDTO> getJsonTableData(ModelMap model) {


        return null;
    }
}
