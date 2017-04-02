package com.nmvk.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Administrator;

public interface AdministratorDao extends CrudRepository<Administrator, Long>{
	
	@Query(value = "SELECT * FROM ADMINISTRATOR WHERE APPUSERID = ?1", nativeQuery = true)
	Administrator getByEmail(String appuserid);
}
