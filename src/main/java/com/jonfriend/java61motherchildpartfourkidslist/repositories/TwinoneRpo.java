package com.jonfriend.java61motherchildpartfourkidslist.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java61motherchildpartfourkidslist.models.TwintwoMdl;
import com.jonfriend.java61motherchildpartfourkidslist.models.TwinoneMdl;

@Repository
public interface TwinoneRpo extends CrudRepository<TwinoneMdl, Long> {
	
	List<TwinoneMdl> findAll();
	
	TwinoneMdl findByIdIs(Long id);
	
	List<TwinoneMdl> findAllByTwintwoMdl(TwintwoMdl twintwoMdl);
	
	List<TwinoneMdl> findByTwintwoMdlNotContains(TwintwoMdl twintwoMdl);
}
