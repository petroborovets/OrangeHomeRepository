package com.boro.black.service.crawler.implementation;

import com.boro.black.dao.ElementDAO;
import com.boro.black.entity.crawler.Company;
import com.boro.black.entity.crawler.SpiderTask;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.crawler.SpiderTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by petroborovets on 7/24/15.
 */
@Transactional
@Service("spiderTaskService")
public class SpiderTaskServiceImpl implements SpiderTaskService{

    @Autowired
    private ElementDAO<SpiderTask> spiderTaskDAO;

    public void addElement(SpiderTask element) throws NonUniqueElementException {
        spiderTaskDAO.addElement(element);
    }

    public void updateElement(SpiderTask element) throws NonUniqueElementException {
        spiderTaskDAO.updateElement(element);
    }

    public List<SpiderTask> getAllElements() {
        return spiderTaskDAO.getAllElements();
    }

    public void deleteElement(SpiderTask element) {
        spiderTaskDAO.deleteElement(element);
    }

    public List<SpiderTask> getElementsByCriteria(Object... criteria) {
        return null;
    }

    public SpiderTask getElementByID(Long elementId) {
        return spiderTaskDAO.getElementByID(elementId);
    }
}
