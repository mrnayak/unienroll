package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.CourseListing;


@Transactional
public interface CourseListingDao extends CrudRepository<CourseListing, Long>{
	
	@Query(value = "select c.CID,o.SCHED_ID,o.CLASSROOM_ID,c.NAME,c.DEPARTMENT,c.CREDIT,o.REMAINING,sc.MON,sc.TUE,sc.WED,sc.THU,sc.FRI,sc.START_HOUR,sc.START_MIN,sc.END_HOUR,sc.END_MIN from OFFERINGS o, COURSE c, CLASSROOMS cl, SCHEDULE sc where o.CID = c.CID and c.COURSE_LEVEL = (select s.LVL from STUDENT s where s.STUDENT_ID = ?3 ) and o.CLASSROOM_ID = cl.ID and o.SCHED_ID = sc.SID and o.SEM = (?1) and o.YEAR = (?2)", nativeQuery = true)
	public List<CourseListing> getOfferingsBySem(String sem,int year, Integer studentId);
	
	@Query(value = "select c.CID,o.SCHED_ID,o.CLASSROOM_ID,c.NAME,c.DEPARTMENT,c.CREDIT,o.REMAINING,sc.MON,sc.TUE,sc.WED,sc.THU,sc.FRI,sc.START_HOUR,sc.START_MIN,sc.END_HOUR,sc.END_MIN from OFFERINGS o, COURSE c, CLASSROOMS cl, SCHEDULE sc where o.CID = c.CID and o.CLASSROOM_ID = cl.ID and o.SCHED_ID = sc.SID and o.SEM = (?1) and o.YEAR = (?2)", nativeQuery = true)
	public List<CourseListing> getOfferingsBySemforAdmin(String sem,int year);
	
	@Query(value = "select  c.CID,e.SCHED_ID,e.CLASSROOM_ID,c.NAME,c.DEPARTMENT,c.CREDIT,o.REMAINING,s.MON,s.TUE,s.WED,s.THU,s.FRI,s.START_HOUR,s.START_MIN,s.END_HOUR,s.END_MIN from ENROLLMENTS e,offerings o,COURSE c,SCHEDULE s where e.SCHED_ID = s.SID and e.CID = c.CID and e.CID = o.CID and e.SCHED_ID = o.SCHED_ID and e.CLASSROOM_ID = o.CLASSROOM_ID and e.SEM = o.sem and e.YEAR = o.YEAR and e.student_id = ?3 and e.sem = ?1 and e.year = ?2 and e.ORDER_NUM <= o.MAX",nativeQuery = true)
	public List<CourseListing> getRegisteredCourseBySem(String sem,int year, Integer student);
	
	@Query(value = "select  c.CID,e.SCHED_ID,e.CLASSROOM_ID,c.NAME,c.DEPARTMENT,c.CREDIT,o.REMAINING,s.MON,s.TUE,s.WED,s.THU,s.FRI,s.START_HOUR,s.START_MIN,s.END_HOUR,s.END_MIN from ENROLLMENTS e,offerings o,COURSE c,SCHEDULE s where e.SCHED_ID = s.SID and e.CID = c.CID and e.CID = o.CID and e.SCHED_ID = o.SCHED_ID and e.CLASSROOM_ID = o.CLASSROOM_ID and e.SEM = o.sem and e.YEAR = o.YEAR and e.student_id = ?1 and e.ORDER_NUM <= o.MAX",nativeQuery = true)
	public List<CourseListing> getRegisteredCourseByStudent(Integer studentId);
	
	@Query(value = "select  c.CID,e.SCHED_ID,e.CLASSROOM_ID,c.NAME,c.DEPARTMENT,c.CREDIT,o.REMAINING,s.MON,s.TUE,s.WED,s.THU,s.FRI,s.START_HOUR,s.START_MIN,s.END_HOUR,s.END_MIN from ENROLLMENTS e,offerings o,COURSE c,SCHEDULE s where e.SCHED_ID = s.SID and e.CID = c.CID and e.CID = o.CID and e.SCHED_ID = o.SCHED_ID and e.CLASSROOM_ID = o.CLASSROOM_ID and e.SEM = o.sem and e.YEAR = o.YEAR and e.student_id = ?3 and e.sem = ?1 and e.year = ?2 and e.ORDER_NUM > o.MAX and e.ORDER_NUM <= (o.MAX+o.waitlist)",nativeQuery = true)
	public List<CourseListing> getWLourseBySem(String sem,int year, Integer student);
	
	@Query(value = "select  c.CID,e.SCHED_ID,e.CLASSROOM_ID,c.NAME,c.DEPARTMENT,c.CREDIT,o.REMAINING,s.MON,s.TUE,s.WED,s.THU,s.FRI,s.START_HOUR,s.START_MIN,s.END_HOUR,s.END_MIN from SPECIAL_REQ e,offerings o,COURSE c,SCHEDULE s where e.SCHED_ID = s.SID and e.CID = c.CID and e.CID = o.CID and e.SCHED_ID = o.SCHED_ID and e.CLASSROOM_ID = o.CLASSROOM_ID and e.SEM = o.sem and e.YEAR = o.YEAR and e.student_id = ?3 and e.sem = ?1 and e.year = ?2",nativeQuery = true)
	public List<CourseListing> getPendingCourseBySem(String sem,int year, Integer studentId);
	
}
