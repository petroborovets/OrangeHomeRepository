package com.boro.black.dao.implementation;

import com.boro.black.entity.SecurityRole;
import com.boro.black.exception.NonUniqueElementException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by petroborovets on 5/11/15.
 */
@Repository("securityRoleDAO")
public class SecurityRoleDAO extends ElementDAOImpl<SecurityRole> {
    public SecurityRoleDAO() {
        super(SecurityRole.class);
    }

    public SecurityRole checkForUnique(SecurityRole element, Session session) throws NonUniqueElementException {
        List<SecurityRole> securityRoleList = getAllElements();
        for (SecurityRole securityRole : securityRoleList) {
            if (securityRole.getName().equals(element.getName()))
                throw new NonUniqueElementException("Failed to create new SecurityRole. " +
                        "SecurityRole with name [" + securityRole.getName() + "] already exist.");
        }
        return element;
    }
}
