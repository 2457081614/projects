package com.meession.education.account.service;

import java.io.InputStream;
import java.util.List;

import com.meession.education.account.model.Role;
import com.meession.education.account.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    
    List<User> findAllUser();
    
    List<Role>  findAllRole();
    
    void importUsers(InputStream in);
    
    /******17.4.21*****/
    public String findTheLoggiedRole();
}
