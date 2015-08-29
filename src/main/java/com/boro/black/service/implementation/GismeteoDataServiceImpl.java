package com.boro.black.service.implementation;

import com.boro.black.dao.ElementDAO;
import com.boro.black.dto.GismeteoDataDTO;
import com.boro.black.entity.GismeteoData;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.GismeteoDataService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petroborovets on 6/10/15.
 */
@Transactional
@Service("gismeteoDataService")
public class GismeteoDataServiceImpl implements GismeteoDataService {

    static Logger log = Logger.getLogger(GismeteoDataServiceImpl.class.getName());

    @Autowired
    private ElementDAO<GismeteoData> gismeteoDataDAO;

    public void addElement(GismeteoData element) throws NonUniqueElementException {
        gismeteoDataDAO.addElement(element);
    }

    public void updateElement(GismeteoData element) throws NonUniqueElementException {
        gismeteoDataDAO.updateElement(element);
    }

    public List<GismeteoData> getAllElements() {
        return gismeteoDataDAO.getAllElements();
    }

    public void deleteElement(GismeteoData element) {
        gismeteoDataDAO.deleteElement(element);
    }

    public List<GismeteoData> getElementsByCriteria(Object... criteria) {
        return null;
    }

    public GismeteoData getElementByID(Long elementId) {
        return gismeteoDataDAO.getElementByID(elementId);
    }

    public GismeteoData getElementSavedSinseLastHour() {
        GismeteoData gismeteoDataSavedToday = null;

        for (GismeteoData gismeteoData : getAllElements()) {
            DateTime creationDate = new DateTime(gismeteoData.getCreateDate());
            DateTime currentTime = new DateTime();

            Minutes minutes = Minutes.minutesBetween(creationDate, currentTime);

            if (minutes.getMinutes() < 60) {
                gismeteoDataSavedToday = gismeteoData;
            }
        }

        return gismeteoDataSavedToday;
    }

    public GismeteoDataDTO getElementDTO(GismeteoData element) {
        GismeteoDataDTO dto = new GismeteoDataDTO();

        dto.setId(element.getId());
        dto.setWindSpeed(element.getWindSpeed());
        dto.setPressure(element.getPressure());
        dto.setWeatherImageUrl(element.getWeatherImageUrl());
        dto.setWaterTemperature(element.getWaterTemperature());
        dto.setHumidity(element.getHumidity());
        dto.setTemperature(element.getTemperature());

        //converting date
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dto.setCreateDate(df.format(element.getCreateDate()));

        return dto;
    }

    public ArrayList<GismeteoDataDTO> getElementsDTOs(List<GismeteoData> elements) {
        ArrayList<GismeteoDataDTO> gismeteoDataDTOArrayList = new ArrayList<GismeteoDataDTO>();

        for (GismeteoData element : elements) {
            gismeteoDataDTOArrayList.add(getElementDTO(element));
        }

        return gismeteoDataDTOArrayList;
    }
}
