package com.boro.black.service.implementation;

import com.boro.black.dao.ElementDAO;
import com.boro.black.entity.SecurityRole;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.SecurityRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by petroborovets on 5/11/15.
 */
@Transactional
@Service("securityRoleService")
public class SecurityRoleServiceImpl implements SecurityRoleService {

    @Autowired
    private ElementDAO<SecurityRole> securityRoleDAO;

    public void addElement(SecurityRole element) throws NonUniqueElementException {
        securityRoleDAO.addElement(element);
    }

    public void addAllElements(List<SecurityRole> element) throws NonUniqueElementException {
        securityRoleDAO.addAllElements(element);
    }

    public void updateElement(SecurityRole element) {
        securityRoleDAO.updateElement(element);
    }

    public List<SecurityRole> getAllElements() {
        return securityRoleDAO.getAllElements();
    }

    public void deleteElement(SecurityRole element) {
        securityRoleDAO.deleteElement(element);
    }

    public List<SecurityRole> getElementsByCriteria(Object... criteria) {
        return securityRoleDAO.getElementsByCriteria(criteria);
    }

    public SecurityRole getElementByID(Long elementId) {
        return securityRoleDAO.getElementByID(elementId);
    }
}
