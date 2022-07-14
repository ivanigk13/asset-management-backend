package com.lawencon.asset.service;

import com.lawencon.asset.dto.employee.DeleteEmployeeDtoRes;
import com.lawencon.asset.dto.employee.GetAllEmployeeDto;
import com.lawencon.asset.dto.employee.GetByIdEmployeeDto;
import com.lawencon.asset.dto.employee.InsertEmployeeDtoReq;
import com.lawencon.asset.dto.employee.InsertEmployeeDtoRes;
import com.lawencon.asset.dto.employee.UpdateEmployeeDtoReq;
import com.lawencon.asset.dto.employee.UpdateEmployeeDtoRes;

public interface EmployeeService {
	GetAllEmployeeDto getAll();
	GetByIdEmployeeDto getById(Long id);
	InsertEmployeeDtoRes insert(InsertEmployeeDtoReq employee);
	UpdateEmployeeDtoRes update(UpdateEmployeeDtoReq employee);
	DeleteEmployeeDtoRes delete(Long id);
}
