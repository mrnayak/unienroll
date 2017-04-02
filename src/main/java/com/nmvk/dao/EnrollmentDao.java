package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Faculty;
import com.nmvk.domain.Semester;
import com.nmvk.domain.Student;

@Transactional
public interface EnrollmentDao extends CrudRepository<Faculty, Long>{
	
	@Modifying
	@Query(value = "insert into enrollments values(?1,null,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
	public void addToEnrollment(int student_id,int schedule_id,int classroom_id,int CID,int order_num,String sem,String year);
	
}
