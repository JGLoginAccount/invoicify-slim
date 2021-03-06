package com.lmig.gfc.invoicify.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Invoice;
import com.lmig.gfc.invoicify.models.InvoiceLineItem;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordsRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;
import com.lmig.gfc.invoicify.services.InvoiceRepository;

@Controller
@RequestMapping("/invoices")
public class InvoicesController {

	private InvoiceRepository invoiceRepository;
	private CompanyRepository companyRepository;
	private BillingRecordsRepository billingRecordRepository;

	public InvoicesController(InvoiceRepository invoiceRepository, CompanyRepository companyRepository,BillingRecordsRepository billingRecordRepository) {
		this.invoiceRepository = invoiceRepository;
		this.companyRepository = companyRepository;
		this.billingRecordRepository = billingRecordRepository;
	}

	@GetMapping("")
	public ModelAndView showInvoices() {
		ModelAndView mv = new ModelAndView("invoices/list");
		mv.addObject("invoices", invoiceRepository.findAll());
		mv.addObject("showTable", invoiceRepository.findAll().size()>0);

		return mv;
	}

	@GetMapping("/clients")
	public ModelAndView chooseClient() {
		ModelAndView mv = new ModelAndView("invoices/clients");

		mv.addObject("clients", companyRepository.findAll());

		return mv;
	}

	@GetMapping("/clients/{clientId}")
	public ModelAndView createInvoice(@PathVariable Long clientId) {
		ModelAndView mv = new ModelAndView("invoices/billing-records-list");
		
		List<BillingRecord> records=billingRecordRepository.findByClientIdAndLineItem(clientId,null);
		mv.addObject("records", records);
		mv.addObject("clientId", clientId);

		return mv;
	}

	@PostMapping("/clients/{clientId}")
	public String createInvoice(Invoice invoice, @PathVariable Long clientId, long[] recordIds, Authentication auth) {
		
		User user=(User) auth.getPrincipal();
		ArrayList<BillingRecord> newRecords=new ArrayList<BillingRecord>();
		
		if (recordIds!=null) {
		
		for (int i=0;i<recordIds.length;i=i+1) {
			newRecords.add(billingRecordRepository.findOne(recordIds[i]));	
		}
		List<InvoiceLineItem> invoiceLineItems=new ArrayList<InvoiceLineItem>();
		
		for (int i=0;i<newRecords.size();i=i+1) {
			InvoiceLineItem ili=new InvoiceLineItem();
			ili.setBillingRecord(newRecords.get(i));
			invoice.getInvoices().add(ili);
			ili.setCreatedBy(user);
			ili.setInvoice(invoice);
			invoiceLineItems.add(ili);
		}
		
		invoice.setInvoices(invoiceLineItems);
		invoice.setCreatedBy(user);
		invoice.setCompany(companyRepository.findOne(clientId));
		invoiceRepository.save(invoice);
		}
		
		return "redirect:/invoices";
	}

}
