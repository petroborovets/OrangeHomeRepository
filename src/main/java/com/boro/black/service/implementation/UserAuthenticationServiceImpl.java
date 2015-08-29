package com.boro.black.service.implementation;

import com.boro.black.entity.SecurityRole;
import com.boro.black.entity.User;
import com.boro.black.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by petroborovets on 5/3/15.
 */
@Service("userAuthenticationServiceImpl")
public class UserAuthenticationServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    static Logger log = Logger.getLogger(UserAuthenticationServiceImpl.class.getName());

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        log.info("Started looking for user with username: [" + s + "]");

        User foundUser = null;

        List<User> userArrayList = userService.getAllElements();
        for (User user : userArrayList) {
            if (user.getEmail().equals(s)) {
                foundUser = user;
                log.info("User with username: [" + s + "] successfully found.");
                break;
            }
        }
        if (foundUser == null) {
            String errorMsg = "User with username: [" + s + "] was not found.";
            log.error(errorMsg);
            throw new UsernameNotFoundException(errorMsg);
        }

        Set<SecurityRole> foundUserRoles = foundUser.getRoles();
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (SecurityRole role : foundUserRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(foundUser.getEmail(),
                foundUser.getPassword(), true, true, true, true, authorities);
    }
}
