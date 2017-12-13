package com.lmig.gfc.invoicify.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;

@Repository
public interface BillingRecordsRepository extends JpaRepository<BillingRecord, Long> {
	
	List<BillingRecord> findByClientId(Long ClientId);
	
	List<BillingRecord> findById(Long Id);

}
