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

import com.lawencon.asset.dto.employee.DeleteEmployeeDtoRes;
import com.lawencon.asset.dto.employee.GetAllEmployeeDto;
import com.lawencon.asset.dto.employee.GetByIdEmployeeDto;
import com.lawencon.asset.dto.employee.InsertEmployeeDtoReq;
import com.lawencon.asset.dto.employee.InsertEmployeeDtoRes;
import com.lawencon.asset.dto.employee.UpdateEmployeeDtoReq;
import com.lawencon.asset.dto.employee.UpdateEmployeeDtoRes;
import com.lawencon.asset.service.EmployeeService;

@RestController
@RequestMapping("employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllEmployeeDto> getAll(){
		GetAllEmployeeDto result = employeeService.getAll();
		return new ResponseEntity<GetAllEmployeeDto>(result, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByIdEmployeeDto> getById(@PathVariable("id") Long id){
		GetByIdEmployeeDto result = employeeService.getById(id);
		return new ResponseEntity<GetByIdEmployeeDto>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertEmployeeDtoRes> insert(@RequestBody @Valid InsertEmployeeDtoReq employee){
		InsertEmployeeDtoRes result = employeeService.insert(employee);
		return new ResponseEntity<InsertEmployeeDtoRes>(result, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateEmployeeDtoRes> update(@RequestBody @Valid UpdateEmployeeDtoReq employee){
		UpdateEmployeeDtoRes result = employeeService.update(employee);
		return new ResponseEntity<UpdateEmployeeDtoRes>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteEmployeeDtoRes> delete(@PathVariable("id") Long id){
		DeleteEmployeeDtoRes result = employeeService.delete(id);
		return new ResponseEntity<DeleteEmployeeDtoRes>(result, HttpStatus.OK);
	}
	
}
