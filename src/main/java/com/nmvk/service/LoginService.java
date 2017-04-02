package com.nmvk.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmvk.dao.AppUserDao;
import com.nmvk.domain.AppUser;

@Service
public class LoginService {

	@Autowired
	AppUserDao appUserDao;

	@Autowired
	Scanner scanner;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	StudentService studentService;
	
	AppUser appUser = null;
	
	public void login() {
		while (true) {
			System.out.println("1. Please enter Username");
			String username = scanner.next();

			System.out.println("2. Please enter Password");
			String password = scanner.next();

			AppUser appUser = appUserDao.getByUsernameAndPassWord(username, password);
			
			if(appUser == null) {
				System.out.println("Invalid Username or password");
				continue;
			}
			
			this.appUser = appUser;
			break;
		}
		
		if(appUser.getIsAdmin()) {
			adminService.mainMenu();
		} else {
			studentService.mainMenu();
		}

	}
}
