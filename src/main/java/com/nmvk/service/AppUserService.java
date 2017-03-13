package com.nmvk.service;

import java.util.List;

import com.nmvk.domain.AppUser;

/**
 * Service layer for AppUser. Business logic would be defined here.
 * 
 * @author Raghav
 *
 */
public interface AppUserService {

	/**
	 * Get list of all app users.
	 * 
	 * @return List of App users
	 */
	public List<AppUser> getAllUsers();
}
