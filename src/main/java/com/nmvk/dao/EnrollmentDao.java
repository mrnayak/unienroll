package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Faculty;
import com.nmvk.domain.Semester;
import com.nmvk.domain.Student;

@Transactional
public interface EnrollmentDao extends CrudRepository<Faculty, Long>{
	
	@Query(value = "insert into enrollment values(?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
	public void addToEnrollment(int student_id,float gpa,int schedule_id,int classroom_id,int CID,int order_num,String sem,String year);
	
}
