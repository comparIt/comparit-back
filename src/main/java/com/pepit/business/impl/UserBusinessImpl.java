package com.pepit.business.impl;

import com.pepit.business.UserBusiness;
import com.pepit.model.User;
import com.pepit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessImpl implements UserBusiness {

    private UserService userService;

    @Autowired
    public UserBusinessImpl(UserService userService) {
        this.userService = userService;
    }
}
