package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Offering;
import com.nmvk.domain.Prerequisite;

@Transactional
public interface PreReqDao extends CrudRepository<Prerequisite, Long> {
	
	
	@Query(value = "SELECT * FROM PREREQ WHERE cID = ?1", nativeQuery = true)
	public List<Prerequisite> getPreReqCourse(int CId);
}
