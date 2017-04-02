package com.nmvk.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Schedule;

public interface ScheduleDao extends CrudRepository<Schedule, Long>{
	
	@Query(value = "SELECT * FROM SCHEDULE WHERE MON = ?1 and TUE = ?2 AND WED = ?3 AND THU = ?4 AND FRI = ?5 "
			+ "AND START_HOUR = ?6 AND START_MIN = ?7 AND END_HOUR = ?8 AND END_MIN = ?9", nativeQuery = true)
	Integer getSchedule(Integer mon, Integer tuesday, Integer wed, Integer thu, Integer fri,
			Integer startHour, Integer startMin, Integer endHour, Integer endMin);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO SCHEDULE (MON, TUE, WED, THU, FRI, START_HOUR, START_MIN, END_HOUR, END_MIN) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery = true)
	void insert(Integer mon, Integer tuesday, Integer wed, Integer thu, Integer fri,
			Integer startHour, Integer startMin, Integer endHour, Integer endMin);
}
