package com.boro.black.service.implementation;

import com.boro.black.dao.ElementDAO;
import com.boro.black.entity.User;
import com.boro.black.exception.NonUniqueElementException;
import com.boro.black.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petroborovets on 4/14/15.
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private ElementDAO<User> userDAO;

    public void addElement(User user) throws NonUniqueElementException {
        userDAO.addElement(user);
    }

    public void updateElement(User user) throws NonUniqueElementException {
        userDAO.updateElement(user);
    }

    public List<User> getAllElements() {
        return userDAO.getAllElements();
    }

    public List<User> filterUsers(List<User> userList) {
        for (int i = 0; i < userList.size(); i++) {
            for (int j = 0; j < userList.size(); j++) {
                if (userList.get(i).getEmail().equals(userList.get(j).getEmail())
                        && i != j) {
                    userList.remove(j);
                }
            }
        }

        return userList;
    }

    public void deleteElement(User user) {
        userDAO.deleteElement(user);
    }

    //TODO implement
    public List<User> getElementsByCriteria(Object... criteria) {
        return userDAO.getElementsByCriteria(criteria);
    }

    public List<User> getPageOfUsers(int recordsPerPage, int currentPage) {
        int firstElement = currentPage * recordsPerPage;
        int lastElement = firstElement + recordsPerPage;

        List<User> listOfAllUsers = filterUsers(getAllElements());
        int numberOfUsers = listOfAllUsers.size();
        log.info("#getPageOfUsers: number of users in system: " + numberOfUsers);

        if (firstElement >= numberOfUsers) {
            return new ArrayList<User>(); // empty
        }
        if (lastElement >= numberOfUsers) {
            return listOfAllUsers.subList(firstElement, numberOfUsers);
        }

        return listOfAllUsers.subList(firstElement, lastElement);
    }

    public User getElementByID(Long userId) {
        return userDAO.getElementByID(userId);
    }

    public User getUserByEmailAddress(String userEmailAddress) {
        return userDAO.getElementByQuery("SELECT * FROM USERS WHERE EMAIL LIKE '" + userEmailAddress + "';");
    }

    public User getLoggedInUser() {
        Object springUserObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (springUserObject == null || !(springUserObject instanceof org.springframework.security.core.userdetails.User)) {
            log.warn("Cant determine logged in user.");
            return null;
        }
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) springUserObject;

        String userEmailAddress = springUser.getUsername();
        if (userEmailAddress == null) {
            String errorMsg = "Failed to find user by email address[" + userEmailAddress + "]";
            log.error(errorMsg);
        }

        return getUserByEmailAddress(userEmailAddress);
    }
}
