package com.lawencon.asset.dao.impl.hibernatenative;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.EmployeeDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Company;
import com.lawencon.asset.model.Employee;
import com.lawencon.asset.model.Users;

@Profile("native")
@Repository
public class EmployeeDaoHibernateNativeImpl extends BaseDaoImpl implements EmployeeDao{

	@Override
	public List<Employee> getAll() {
		String sql = "SELECT id, user_id, company_id, nip, employee_name, created_by, created_at, "
				+ "updated_by, updated_at, version, is_active "
				+ "FROM employee";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<Employee> employees = new ArrayList<Employee>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			Employee employee = new Employee();
			employee.setId(Long.valueOf(obj[0].toString()));
			Users user = new Users();
			user.setId(Long.valueOf(obj[1].toString()));
			Company company = new Company();
			company.setId(Long.valueOf(obj[2].toString()));
			
			employee.setUser(user);
			employee.setCompany(company);
			employee.setNip(obj[3].toString());
			employee.setEmployeeName(obj[4].toString());
			
			if(obj[5]!=null)employee.setCreatedBy(Long.valueOf(obj[5].toString()));
			if(obj[6]!=null)employee.setCreatedAt(Timestamp.valueOf(obj[6].toString()).toLocalDateTime());			
			
			if(obj[7]!=null) employee.setUpdatedBy(Long.valueOf(obj[7].toString()));
			if(obj[8]!=null) employee.setUpdatedAt(Timestamp.valueOf(obj[8].toString()).toLocalDateTime());
			
			employee.setVersion(Integer.valueOf(obj[9].toString()));
			employee.setIsActive(Boolean.valueOf(obj[10].toString()));
			employees.add(employee);
		});
		return employees;
	}

	@Override
	public Employee save(Employee employee) {
		if(employee.getId()!=null) {
			employee = em.merge(employee);
			em.flush();
		}else {
			em.persist(employee);
		}
		em.detach(employee);
		return employee;
	}

	@Override
	public Employee getById(Long id) {
		Employee employee = new Employee();
		try{
			employee = em.find(Employee.class, id);
			em.detach(employee);
		}catch (Exception e){
			employee = null;
		}
		return employee;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM employee WHERE id = :id";
		int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result>0;
	}

}
