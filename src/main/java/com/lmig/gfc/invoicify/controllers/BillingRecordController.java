package com.lmig.gfc.invoicify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.services.BillingRecordsRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@Controller
@RequestMapping("/billing-records")
public class BillingRecordController {
	
	private BillingRecordsRepository billingRecordRepository;
	private CompanyRepository companyRepository;
	
	public BillingRecordController (BillingRecordsRepository billingRecordRepository, CompanyRepository companyRepository) {
		this.billingRecordRepository=billingRecordRepository;
		this.companyRepository=companyRepository;
	}

	@GetMapping("")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("billing-records/list");
		
		
		mv.addObject("records",billingRecordRepository.findAll());	
		mv.addObject("companies",companyRepository.findAll());	
		
		return mv;
	}

}
