package com.nmvk.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Student;

@Transactional
public interface StudentDao extends CrudRepository<Student, Long>{
	@Modifying
	@Query(value = "INSERT INTO STUDENT(STUDENT_ID, F_NAME, L_NAME, DOB, LVL, RESIDENCY, BILL) VALUES (?1, ?2,?3, ?4, ?5,?6,?7)", nativeQuery = true)
	void insert(Integer studentId, String firstName, String lastName, String dateOfBirth, Integer level, Integer residency
			,Integer amount);
	
	@Query(value = "SELECT * FROM STUDENT WHERE STUDENT_ID = ?1", nativeQuery = true)
	Student getById(int studentId);
}
