package com.boro.black.service;

import com.boro.black.dto.GismeteoDataDTO;
import com.boro.black.entity.GismeteoData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petroborovets on 6/10/15.
 */
public interface GismeteoDataService extends ElementService<GismeteoData> {
    public GismeteoData getElementSavedSinseLastHour();

    public GismeteoDataDTO getElementDTO(GismeteoData element);

    public ArrayList<GismeteoDataDTO> getElementsDTOs(List<GismeteoData> elements);
}
