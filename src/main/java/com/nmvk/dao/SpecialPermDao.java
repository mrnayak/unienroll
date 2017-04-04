package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.SpecialReq;

@Transactional
public interface SpecialPermDao extends CrudRepository<SpecialReq, Long>{
	
	@Query(value = "SELECT * FROM SPECIAL_REQ WHERE IS_APPROVED = 0", nativeQuery = true)
	public List<SpecialReq> getAllPendingSpecialReqs();
	
	@Modifying
	@Query(value = "insert into SPECIAL_REQ(STUDENT_ID,GPA,SCHED_ID,CLASSROOM_ID,CID,ORDER_NUM,SEM,YEAR) values(?1,null,?2,?3,?4,0,?5,?6) where not exists (select * )", nativeQuery = true)
	public void addSpecialPerm(Integer studentId, Integer schedId, Integer classroomId, Integer cid,String sem,int year);
	
	@Modifying
	@Query(value = "DELETE FROM SPECIAL_REQ WHERE STUDENT_ID = ?1 AND SCHED_ID = ?2 AND CLASSROOM_ID = ?3 ", nativeQuery = true)
	public void deleteSpecialPerm(Integer studentId, Integer schedId, Integer classroomId);
}
