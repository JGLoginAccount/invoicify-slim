package com.lmig.gfc.invoicify.controllers;
 
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordsRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@Controller
@RequestMapping("/billing-records/flat-fees")
public class FlatFeeBillingRecordController {
	 
	private CompanyRepository companyRepository;
	private BillingRecordsRepository billingyRepository;
	
	public FlatFeeBillingRecordController(CompanyRepository companyRepository, BillingRecordsRepository billingyRepository) {
		this.billingyRepository=billingyRepository;
		this.companyRepository=companyRepository;
		 
	}

	@PostMapping("")
	public ModelAndView create(FlatFeeBillingRecord record, long clientId, Authentication auth) {
		

		User user=(User) auth.getPrincipal();		
		Company company = companyRepository.findOne(clientId);
		record.setClient(company);
		record.setCreatedBy(user);
		billingyRepository.save(record);

		return new ModelAndView("redirect:/billing-records");
	}
	
}
