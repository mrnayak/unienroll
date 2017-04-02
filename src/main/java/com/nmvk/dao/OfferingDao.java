package com.nmvk.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Offering;

public interface OfferingDao extends CrudRepository<Offering, Long> {
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO OFFERING (CID, SCHED_ID, CLASSROOM_ID, MAX, WAITLIST, REMAINING, SEM, YEAR) "
			+ " VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
	void insert(Integer cid, Integer sId, Integer classroomId, Integer max, Integer waitList, Integer remaining, String sem,
			Integer year);
}
