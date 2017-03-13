package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nmvk.domain.AppUser;

/**
 * Spring data jpa repository for APPUSER table.
 * @author Raghav
 *
 */
@Transactional
@Repository
public interface AppUserDao extends CrudRepository<AppUser, Long>{
	
	@Query(value = "SELECT * FROM AppUser", nativeQuery = true)
	public List<AppUser> getAllUsers();
}
