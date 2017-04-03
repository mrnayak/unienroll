package com.nmvk.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Enrollments;


@Transactional
public interface EnrollmentDao extends CrudRepository<Enrollments, Long>{
	
	@Modifying
	@Query(value = "INSERT INTO ENROLLMENTS(STUDENT_ID, GPA, SCHED_ID, CLASSROOM_ID, CID, ORDER_NUM, SEM, YEAR) VALUES(?1,null,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
	public void addToEnrollment(int student_id,int schedule_id,int classroom_id,int CID,int order_num,String sem,String year);
	
	@Query(value = "SELECT * FROM ENROLLMENTS WHERE STUDENT_ID = ?1 AND SCHED_ID = ?2 AND CLASSROOM_ID = ?3 AND CID = ?4 AND SEM = ?5 AND YEAR = ?6 ", nativeQuery = true)
	public Enrollments getByIds(Integer studentId, Integer scheduleId, Integer classroomId, Integer courseId, String sem, String year);
	
	@Query(value = "SELECT ORDER_NUM FROM ENROLLMENTS WHERE STUDENT_ID = ?1 AND SCHED_ID = ?2 AND CLASSROOM_ID = ?3 AND CID = ?4 AND SEM = ?5 AND YEAR = ?6 ", nativeQuery = true)
	public Integer getOrderNumber(Integer studentId, Integer scheduleId, Integer classroomId, Integer courseId, String sem, String year);
	
	@Modifying
	@Query(value = "UPDATE ENROLLMENTS SET ORDER_NUM = ORDER_NUM - 1 WHERE ORDER_NUM > ?1 AND SCHED_ID = ?2 AND CLASSROOM_ID = ?3 AND CID = ?4 AND SEM = ?5 AND YEAR = ?6", nativeQuery = true)
	public void decrementOrder(Integer orderNumber, Integer scheduleId, Integer classroomId, Integer courseId, String sem, String year);
	
	@Modifying
	@Query(value = "DELETE FROM ENROLLMENTS WHERE STUDENT_ID = ?1 AND SCHED_ID = ?2 AND CLASSROOM_ID = ?3 AND CID = ?4 AND SEM = ?5 AND YEAR = ?6 ", nativeQuery = true)
	public void dropEnrollment(Integer studentId, Integer scheduleId, Integer classroomId, Integer courseId, String sem, String year);
	
	
}
