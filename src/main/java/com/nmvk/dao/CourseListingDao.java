package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Course;
import com.nmvk.domain.CourseListing;
import com.nmvk.domain.Semester;


@Transactional
public interface CourseListingDao extends CrudRepository<CourseListing, Long>{
	
	@Query(value = "select c.CID,o.SCHED_ID,o.CLASSROOM_ID,c.NAME,c.DEPARTMENT,c.CREDIT,o.REMAINING,sc.MON,sc.TUE,sc.WED,sc.THU,sc.FRI,sc.START_HOUR,sc.START_MIN,sc.END_HOUR,sc.END_MIN from OFFERINGS o, COURSE c, CLASSROOMS cl, SCHEDULE sc where o.CID = c.CID and c.COURSE_LEVEL = (select s.LVL from STUDENT s where s.STUDENT_ID = 1 ) and o.CLASSROOM_ID = cl.ID and o.SCHED_ID = sc.SID and o.SEM = ?1 and o.YEAR = ?2", nativeQuery = true)
	public List<CourseListing> getOfferingsBySem(String sem,int year);
	
}
