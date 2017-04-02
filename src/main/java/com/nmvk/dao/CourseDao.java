package com.nmvk.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Course;

@Transactional
public interface CourseDao extends CrudRepository<Course, Long>{
	@Modifying
	@Query(value = "INSERT INTO COURSE(CID, NAME, DEPARTMENT, CREDIT, COURSE_LEVEL) VALUES (?1, ?2,?3, ?4, ?5)", nativeQuery = true)
	void insert(Integer cId, String name, String department, Integer credit, Integer courseLevel);
}
