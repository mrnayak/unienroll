package com.nmvk.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Course;

public interface CourseDao extends CrudRepository<Course, Long>{
	@Modifying
	@Query(value = "INSERT INTO COURSE(CID, NAME, DEPARTMENT, CREDIT, COURSE_LEVEL) VALUES (?1, ?2,?3, ?4, ?5)", nativeQuery = true)
	void insert(Integer cId, String name, String department, Integer credit, String courseLevel);
}
