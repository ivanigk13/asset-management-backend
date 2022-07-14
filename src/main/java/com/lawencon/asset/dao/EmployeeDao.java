package com.lawencon.asset.dao;

import java.util.List;

import com.lawencon.asset.model.Employee;

public interface EmployeeDao {
	List<Employee> getAll();
	Employee save(Employee employee);
	Employee getById(Long id);
	boolean delete(Long id);
}
