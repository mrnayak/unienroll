package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Faculty;

@Transactional
public interface FacultyDao extends CrudRepository<Faculty, Long>{
	
	@Query(value = "SELECT * FROM FACULTY WHERE NAME = ?1", nativeQuery = true)
	Faculty getByName(String name);
	
	@Modifying
	@Query(value = "INSERT INTO FACULTY (NAME) VALUES (?1)", nativeQuery = true)
	void insert(String name);
	
	@Query(value = "SELECT FID FROM FACULTY WHERE NAME IN ?1", nativeQuery = true)
	List<String> getFidByName(List<String> names);
	
	@Query(value = "select f.FID,f.NAME,f.DEPARTMENT from Faculty_group fg,Faculty f where fg.FID = f.FID and fg.COURSE_ID = ?1 and fg.SCHED_ID = ?2 and fg.CLASSROOM_ID = ?3;", nativeQuery = true)
	public List<Faculty> getFacultyListForCourse(int course_id,int sched_id,int classroom_id);
	
}
