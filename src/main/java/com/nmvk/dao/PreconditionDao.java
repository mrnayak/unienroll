package com.nmvk.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nmvk.domain.Offering;
import com.nmvk.domain.Preconditionrequirement;
import com.nmvk.domain.Prerequisite;

@Transactional
public interface PreconditionDao extends CrudRepository<Preconditionrequirement, Long> {
	
	
	@Query(value = "SELECT cid,tag,0 as GPA FROM PRECONREQ WHERE cID = ?1 and tag = 1", nativeQuery = true)
	public Preconditionrequirement getSpecialPerm(int CId);
	
	@Query(value = "SELECT a.cid,a.tag,b.GPA FROM PRECONREQ a,PRECON b WHERE a.TAG=b.TAG and a.cID = ?1 and a.tag >=2", nativeQuery = true)
	public Preconditionrequirement getGPAReq(int CId);
	
}
