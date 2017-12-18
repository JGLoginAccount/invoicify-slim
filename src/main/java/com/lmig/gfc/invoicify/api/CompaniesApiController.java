package com.lmig.gfc.invoicify.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@RestController
@RequestMapping("/api/admin/companies")

public class CompaniesApiController { 

	private CompanyRepository cr;

	public CompaniesApiController(CompanyRepository cr) {
		super();
		this.cr = cr;
	}
	
	@PostMapping("")
	public Company hereWeGo(@RequestBody Company company) {
	return cr.save(company);
	}
	
	
	
}
