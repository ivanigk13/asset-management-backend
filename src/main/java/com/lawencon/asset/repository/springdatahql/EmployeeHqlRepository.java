package com.lawencon.asset.repository.springdatahql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.model.Employee;

@Repository
public interface EmployeeHqlRepository extends JpaRepository<Employee, Long>{

	@Modifying
	int removeById(Long id);
}
