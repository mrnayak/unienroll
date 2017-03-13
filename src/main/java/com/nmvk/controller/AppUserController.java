package com.nmvk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nmvk.domain.AppUser;
import com.nmvk.service.AppUserService;

/**
 * This controller contains end points related to user.
 * 
 * @author Raghav
 */
@RestController
@RequestMapping("/users")
public class AppUserController {

	@Autowired
	private AppUserService userService;
	
	/**
	 * View to be returned.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<AppUser> getAllUsers() {
		return userService.getAllUsers();
	}
}
