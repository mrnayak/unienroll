package com.nmvk.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.FacultyGroup;
import com.nmvk.domain.FacultyGroupKey;

public interface FacultyGroupDao extends CrudRepository<FacultyGroup, FacultyGroupKey> {
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO FACULTY_GROUP (COURSE_ID, SCHED_ID, CLASSROOM_ID, FID, SEM, YEAR) VALUES (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
	void insert(Integer cid, Integer sid, Integer classroomId, Integer fid, String sem,
			Integer year);
}
