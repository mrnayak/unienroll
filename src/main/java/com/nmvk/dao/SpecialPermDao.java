package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Semester;
import com.nmvk.domain.SpecialReq;

@Transactional
public interface SpecialPermDao extends CrudRepository<Semester, Long>{
	
	@Query(value = "SELECT * FROM SPECIAL_REQ", nativeQuery = true)
	public List<SpecialReq> getAllPendingSpecialReqs();
	
}
