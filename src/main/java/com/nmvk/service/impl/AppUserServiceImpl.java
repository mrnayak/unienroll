package com.nmvk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmvk.dao.AppUserDao;
import com.nmvk.domain.AppUser;
import com.nmvk.service.AppUserService;

/**
 * Implementation for AppUserService.
 * 
 * @author Raghav
 *
 */
@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserDao userDao;

	@Override
	public List<AppUser> getAllUsers() {
		return userDao.getAllUsers();
	}

}
