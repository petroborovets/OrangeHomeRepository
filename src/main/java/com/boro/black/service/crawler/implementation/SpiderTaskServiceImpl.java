package com.boro.black.service.crawler.implementation;

import com.boro.black.dao.ElementDAO;
import com.boro.black.dao.crawler.implementation.CompanyDAO;
import com.boro.black.dto.SpiderTaskDTO;
import com.boro.black.entity.crawler.Company;
import com.boro.black.entity.crawler.Email;
import com.boro.black.entity.crawler.SpiderTask;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.crawler.SpiderTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petroborovets on 7/24/15.
 */
@Transactional
@Service("spiderTaskService")
public class SpiderTaskServiceImpl implements SpiderTaskService {

    @Autowired
    private ElementDAO<SpiderTask> spiderTaskDAO;

    @Autowired
    private CompanyDAO companyDAO;

    public void addElement(SpiderTask element) throws NonUniqueElementException {
        spiderTaskDAO.addElement(element);
    }

    public void addAllElements(List<SpiderTask> element) throws NonUniqueElementException {
        spiderTaskDAO.addAllElements(element);
    }

    public void updateElement(SpiderTask element) {
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

    public ArrayList<SpiderTaskDTO> getDtoList(List<SpiderTask> spiderTasks) {
        ArrayList<SpiderTaskDTO> spiderTaskDTOs = new ArrayList<SpiderTaskDTO>();
        if (spiderTasks != null && !spiderTasks.isEmpty())
            for (SpiderTask spiderTask : spiderTasks) {
                // get all emails
                List<Email> allEmails = new ArrayList<Email>();
                for (Company company : companyDAO.getByTaskId(spiderTask)) {
                    allEmails.addAll(company.getEmails());
                }

                // cut url if too long
                String spidername = spiderTask.getName();
                String spiderNameCut = spidername.length() > 40 ? spidername.substring(0, 39) + "...]" : spidername;

                SpiderTaskDTO spiderTaskDTO = new SpiderTaskDTO();

                spiderTaskDTO.setId(spiderTask.getId());
                spiderTaskDTO.setName(spiderNameCut);
                spiderTaskDTO.setNumberOfEmails(allEmails.size());

                String progressLabel;
                if (spiderTask.getProgress() == 1)
                    progressLabel = "Running";
                else if (spiderTask.getProgress() == 2)
                    progressLabel = "Finished";
                else progressLabel = "Undefined";
                spiderTaskDTO.setProgress(progressLabel);
                spiderTaskDTO.setStartDate(spiderTask.getCreateDate().toString());

                spiderTaskDTOs.add(spiderTaskDTO);
            }
        return spiderTaskDTOs;
    }

    public SpiderTaskDTO getDto(SpiderTask spiderTask) {
        SpiderTaskDTO spiderTaskDTO = new SpiderTaskDTO();

        // get all emails
        List<Email> allEmails = new ArrayList<Email>();
        List<Company> taskCompanies = companyDAO.getByTaskId(spiderTask);
        for (Company company : taskCompanies) {
            allEmails.addAll(company.getEmails());
        }
        String spidername = spiderTask.getName();
        //String spiderNameCut = spidername.length() > 40 ? spidername.substring(0, 39) + "...]" : spidername;

        spiderTaskDTO.setId(spiderTask.getId());
        spiderTaskDTO.setName(spiderTask.getName());
        spiderTaskDTO.setDescription(spiderTask.getDescription());
        spiderTaskDTO.setNumberOfEmails(allEmails.size());
        spiderTaskDTO.setSpiderUrl(spiderTask.getUrl());
        spiderTaskDTO.setNumberOfCompanies(taskCompanies.size());

        String progressLabel;
        if (spiderTask.getProgress() == 1)
            progressLabel = "Running";
        else if (spiderTask.getProgress() == 2)
            progressLabel = "Finished";
        else progressLabel = "Undefined";

        spiderTaskDTO.setProgress(progressLabel);
        String createDate = spiderTask.getCreateDate().toString();
        spiderTaskDTO.setStartDate(createDate.substring(0, 11));
        spiderTaskDTO.setStartTime(createDate.substring(11));

        if (spiderTask.getFinishDate() != null) {
            String finishDate = spiderTask.getFinishDate().toString();
            spiderTaskDTO.setFinishDate(finishDate.substring(0, 11));
            spiderTaskDTO.setFinishTime(finishDate.substring(11));
        }

        return spiderTaskDTO;
    }
}
