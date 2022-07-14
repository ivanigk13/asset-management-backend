package com.lawencon.asset.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.asset.dao.EmployeeDao;
import com.lawencon.asset.dto.employee.InsertEmployeeDtoData;
import com.lawencon.asset.dto.employee.InsertEmployeeDtoRes;
import com.lawencon.asset.dto.employee.UpdateEmployeeDtoData;
import com.lawencon.asset.dto.employee.UpdateEmployeeDtoRes;
import com.lawencon.asset.dto.employee.DeleteEmployeeDtoRes;
import com.lawencon.asset.dto.employee.EmployeeModelDto;
import com.lawencon.asset.dto.employee.GetAllEmployeeDto;
import com.lawencon.asset.dto.employee.GetByIdEmployeeDto;
import com.lawencon.asset.dto.employee.InsertEmployeeDtoReq;
import com.lawencon.asset.dto.employee.UpdateEmployeeDtoReq;
import com.lawencon.asset.model.Employee;
import com.lawencon.asset.model.Company;
import com.lawencon.asset.model.Users;
import com.lawencon.asset.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService{

	private EmployeeDao employeeDao;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public GetAllEmployeeDto getAll() {
		GetAllEmployeeDto allEmployeeDto = new GetAllEmployeeDto();
		List<EmployeeModelDto> datas = new ArrayList<EmployeeModelDto>();
		List<Employee> employees = employeeDao.getAll();
		for (Employee employee : employees) {
			EmployeeModelDto data = new EmployeeModelDto();
			data.setId(employee.getId());
			data.setCompanyName(employee.getCompany().getCompanyName());
			data.setNip(employee.getNip());
			data.setEmployeeName(employee.getEmployeeName());
			data.setIsActive(employee.getIsActive());
			data.setVersion(employee.getVersion());
			datas.add(data);
		}
		allEmployeeDto.setData(datas);
		return allEmployeeDto;
	}

	@Override
	public GetByIdEmployeeDto getById(Long id) {
		GetByIdEmployeeDto employeeDto = new GetByIdEmployeeDto();
		EmployeeModelDto data = new EmployeeModelDto();
		Employee employee = employeeDao.getById(id);
		data.setId(employee.getId());
		data.setCompanyId(employee.getCompany().getId());
		data.setCompanyName(employee.getCompany().getCompanyName());
		data.setNip(employee.getNip());
		data.setEmployeeName(employee.getEmployeeName());
		data.setIsActive(employee.getIsActive());
		data.setVersion(employee.getVersion());
		employeeDto.setData(data);
		return employeeDto;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertEmployeeDtoRes insert(InsertEmployeeDtoReq employee) {
		InsertEmployeeDtoRes employeeDtoRes = new InsertEmployeeDtoRes();
		InsertEmployeeDtoData data = new InsertEmployeeDtoData();
		Employee employeeInsert = new Employee();
		Users user = new Users();
		user.setId(employee.getUserId());
		user.setVersion(0);
		Company company = new Company();
		company.setId(employee.getCompanyId());
		company.setVersion(0);
		
		employeeInsert.setUser(user);
		employeeInsert.setCompany(company);
		employeeInsert.setNip(employee.getNip());
		employeeInsert.setEmployeeName(employee.getEmployeeName());
		employeeInsert.setCreatedBy(getUserId());
		Employee newEmployee = employeeDao.save(employeeInsert);
		if(newEmployee!=null) {
			data.setId(newEmployee.getId());
			employeeDtoRes.setData(data);
			employeeDtoRes.setMsg("berhasil insert employee");
		}
		return employeeDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateEmployeeDtoRes update(UpdateEmployeeDtoReq employee) {
		UpdateEmployeeDtoRes employeeDtoRes = new UpdateEmployeeDtoRes();
		UpdateEmployeeDtoData data = new UpdateEmployeeDtoData();
		
		Employee employeeUpdate = employeeDao.getById(employee.getId());
		employeeUpdate.setId(employee.getId());
		employeeUpdate.setVersion(employee.getVersion());
		employeeUpdate.setEmployeeName(employee.getEmployeeName());
		employeeUpdate.setUpdatedBy(getUserId());
		Employee newEmployee = employeeDao.save(employeeUpdate);
		if(newEmployee!=null) {
			data.setVersion(newEmployee.getVersion());
			employeeDtoRes.setData(data);
			employeeDtoRes.setMsg("berhasil update employee");
		}
		return employeeDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteEmployeeDtoRes delete(Long id) {
		DeleteEmployeeDtoRes deleteEmployeeDtoRes = new DeleteEmployeeDtoRes();
		Boolean result = employeeDao.delete(id);
		if(result) deleteEmployeeDtoRes.setMsg("Berhasil delete");
		else deleteEmployeeDtoRes.setMsg("Gagal delete");
		return deleteEmployeeDtoRes;
	}
	
}
