package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Semester;
import com.nmvk.domain.Student;

@Transactional
public interface SemesterDao extends CrudRepository<Semester, Long>{
	
	@Query(value = "SELECT * FROM SEMESTER WHERE STATUS = 1", nativeQuery = true)
	public List<Semester> getActiveSem();
	
}
