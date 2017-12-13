package com.lmig.gfc.invoicify.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;
import com.lmig.gfc.invoicify.models.InvoiceLineItem;

@Repository
public interface BillingRecordsRepository extends JpaRepository<BillingRecord, Long> {
	
	List<BillingRecord> findByClientIdAndLineItem(Long ClientId,InvoiceLineItem Id);
	
	List<BillingRecord> findById(Long Id);

}
