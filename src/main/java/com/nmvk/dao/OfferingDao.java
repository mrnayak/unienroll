package com.nmvk.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Offering;

@Transactional
public interface OfferingDao extends CrudRepository<Offering, Long> {
	
	@Modifying
	@Query(value = "INSERT INTO OFFERINGS (CID, SCHED_ID, CLASSROOM_ID, MAX, WAITLIST, REMAINING, SEM, YEAR) "
			+ " VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
	public void insert(Integer cid, Integer sId, Integer classroomId, Integer max, Integer waitList, Integer remaining, String sem,
			Integer year);
	
	@Query(value = "SELECT * FROM OFFERINGS WHERE CID = ?1 AND SCHED_ID = ?2 AND CLASSROOM_ID = ?3 AND SEM = ?4 AND YEAR = ?5", nativeQuery = true)
	public Offering getByIds(Integer courseId, Integer scheduleId, Integer classRoomId, String semester, Integer year);
	
	@Modifying
	@Query(value = "UPDATE OFFERINGS SET REMAINING = REMAINING - 1 WHERE CID = ?1 AND SCHED_ID = ?2 AND CLASSROOM_ID = ?3 AND SEM = ?4 AND YEAR = ?5", nativeQuery = true)
	public void decrementRemaining(Integer courseId, Integer scheduleId, Integer classroomId, String semester, Integer year);
	
	@Modifying
	@Query(value = "UPDATE OFFERINGS SET REMAINING = REMAINING + 1 WHERE CID = ?1 AND SCHED_ID = ?2 AND CLASSROOM_ID = ?3 AND SEM = ?4 AND YEAR = ?5", nativeQuery = true)
	public void incrementRemaining(Integer courseId, Integer scheduleId, Integer classroomId, String semester, Integer year);
}
