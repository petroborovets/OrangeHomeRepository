package com.boro.black.controller;

import com.boro.black.component.ModelUtil;
import com.boro.black.dto.GismeteoDataDTO;
import com.boro.black.entity.GismeteoData;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.GismeteoDataService;
import com.boro.black.component.GismeteoUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petroborovets on 6/10/15.
 */
@Controller
@RequestMapping("/")
public class WeatherController {
    static Logger log = Logger.getLogger(WeatherController.class.getName());

    @Autowired
    GismeteoDataService gismeteoDataService;
    @Autowired
    ModelUtil modelUtil;
    @Autowired
    GismeteoUtil gismeteoUtil;

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public String home(ModelMap model) {

        log.info("Loading home.jsp");

        //Get element saved today
        GismeteoData currentWeather = gismeteoDataService.getElementSavedSinseLastHour();
        if (currentWeather == null) {
            //No elements saved today, save an element
            currentWeather = gismeteoUtil.getCurrentWeather();

            try {
                gismeteoDataService.addElement(currentWeather);
            } catch (NonUniqueElementException e) {
                log.error("Failed to save [" + currentWeather + "].");
            }
        }

        model.addAttribute("weather", currentWeather);

        modelUtil.warp(model);
        return "weather";
    }

    @RequestMapping(value = "/weather/getJson", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<GismeteoDataDTO> getJsonTableData(ModelMap model) {
        List<GismeteoData> gismeteoDatas = gismeteoDataService.getAllElements();

        return gismeteoDataService.getElementsDTOs(gismeteoDatas);
    }
}
