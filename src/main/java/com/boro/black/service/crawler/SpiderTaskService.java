package com.boro.black.service.crawler;

import com.boro.black.dto.SpiderTaskDTO;
import com.boro.black.entity.crawler.SpiderTask;
import com.boro.black.service.ElementService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petroborovets on 7/24/15.
 */
public interface SpiderTaskService extends ElementService<SpiderTask> {
    ArrayList<SpiderTaskDTO> getDtoList(List<SpiderTask> spiderTasks);
    SpiderTaskDTO getDto(SpiderTask spiderTask);
}
