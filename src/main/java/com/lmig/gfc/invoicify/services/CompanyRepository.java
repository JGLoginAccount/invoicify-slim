package com.lmig.gfc.invoicify.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.User;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	
	
	

}
