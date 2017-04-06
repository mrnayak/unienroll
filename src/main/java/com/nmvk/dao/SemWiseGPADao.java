package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.SemWiseGPA;

@Transactional
public interface SemWiseGPADao extends CrudRepository<SemWiseGPA, Long>{

	//Get Overall GPA
	@Query(value = "select round(sum(GPA*CREDIT)/sum(CREDIT),2) as GPA from enrollments where student_id = ?1 group by student_id", nativeQuery = true)
	public Float getOverallAvgGPA(Integer student_id);
	
	
	@Query(value = "select round(sum(GPA*CREDIT)/sum(CREDIT),2) as GPA, year from enrollments where student_id = ?1 group by student_id, year", nativeQuery = true)
	public List<SemWiseGPA> getSemWiseGPA(Integer student_id);

	
	
	@Query(value = "select e.gpa, c.name from enrollments e, course c where student_id = ?1 and c.cid = e.cid", nativeQuery = true)
	public List<Object[]> getAllCourseGPAs(Integer student_id);
	/*TODO: add proper query for below*/
//	@Modifying
//	@Query(value = "UPDATE STUDENT SET EMAIL = ?2 WHERE STUDENT_ID = ?1", nativeQuery = true)
//	void updateEmail(Integer studentId, String email);
	
}
