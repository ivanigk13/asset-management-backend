package com.lawencon.asset.dao.impl.springdatahql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.EmployeeDao;
import com.lawencon.asset.model.Employee;
import com.lawencon.asset.repository.springdatahql.EmployeeHqlRepository;

@Profile("jpahql")
@Repository
public class EmployeeDaoSpringDataHqlImpl implements EmployeeDao{

	private EmployeeHqlRepository employeeHqlRepository;
	
	@Autowired
	public void setEmployeeHqlRepository(EmployeeHqlRepository employeeHqlRepository) {
		this.employeeHqlRepository = employeeHqlRepository;
	}

	@Override
	public List<Employee> getAll() {
		return employeeHqlRepository.findAll();
	}

	@Override
	public Employee save(Employee employee) {
		return employeeHqlRepository.saveAndFlush(employee);
	}

	@Override
	public Employee getById(Long id) {
		return employeeHqlRepository.getById(id);
	}

	@Override
	public boolean delete(Long id) {
		int result = employeeHqlRepository.removeById(id);
		return result>0;
	}

}
