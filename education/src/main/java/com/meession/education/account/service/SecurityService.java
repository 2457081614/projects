package com.meession.education.account.service;

/**
 * 
 * @author sam
 *
 */
public interface SecurityService {
	
    String findLoggedInUsername();
    
    void autologin(String username, String password);
}
