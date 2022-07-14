package com.lawencon.asset.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.asset.dto.company.DeleteCompanyDtoRes;
import com.lawencon.asset.dto.company.GetAllCompanyDto;
import com.lawencon.asset.dto.company.GetByIdCompanyDto;
import com.lawencon.asset.dto.company.InsertCompanyDtoReq;
import com.lawencon.asset.dto.company.InsertCompanyDtoRes;
import com.lawencon.asset.dto.company.UpdateCompanyDtoReq;
import com.lawencon.asset.dto.company.UpdateCompanyDtoRes;
import com.lawencon.asset.service.CompanyService;

@RestController
@RequestMapping("companies")
public class CompanyController {

	private CompanyService companyService;

	@Autowired
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllCompanyDto> getAll(){
		GetAllCompanyDto result = companyService.getAll();
		return new ResponseEntity<GetAllCompanyDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdCompanyDto> getById(@PathVariable("id") Long id){
		GetByIdCompanyDto result = companyService.getById(id);
		return new ResponseEntity<GetByIdCompanyDto>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertCompanyDtoRes> insert(@RequestBody @Valid InsertCompanyDtoReq company){
		InsertCompanyDtoRes result = companyService.insert(company);
		return new ResponseEntity<InsertCompanyDtoRes>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateCompanyDtoRes> update(@RequestBody @Valid UpdateCompanyDtoReq company){
		UpdateCompanyDtoRes result = companyService.update(company);
		return new ResponseEntity<UpdateCompanyDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteCompanyDtoRes> delete(@PathVariable("id") Long id){
		DeleteCompanyDtoRes result = companyService.delete(id);
		return new ResponseEntity<DeleteCompanyDtoRes>(result, HttpStatus.OK);
	}
	
}
