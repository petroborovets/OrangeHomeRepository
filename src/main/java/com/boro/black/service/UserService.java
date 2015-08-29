package com.boro.black.service;

import com.boro.black.entity.User;

/**
 * Created by petroborovets on 4/14/15.
 */
public interface UserService extends ElementService<User> {
    User getUserByEmailAddress(String userEmailAddress);
    User getLoggedInUser();
}
