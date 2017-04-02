package com.nmvk.dao;

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
	
	@Query(value = "SELECT * FROM AppUser WHERE USERNAME = ?1 AND PASSWORD = ?2", nativeQuery = true)
	public AppUser getByUsernameAndPassWord(String userName, String password);
}
