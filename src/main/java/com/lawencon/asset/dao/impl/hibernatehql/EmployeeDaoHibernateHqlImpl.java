package com.lawencon.asset.dao.impl.hibernatehql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.EmployeeDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Employee;

@Profile("hql")
@Repository
public class EmployeeDaoHibernateHqlImpl extends BaseDaoImpl implements EmployeeDao{

	@Override
	public List<Employee> getAll() {
		String sql = "SELECT e FROM Employee as e "
				+ "INNER JOIN FETCH e.company as c "
				+ "ORDER BY e.id";
		List<Employee> employees = em.createQuery(sql, Employee.class).getResultList();
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
		Employee employee = em.find(Employee.class, id);
		em.detach(employee);
		return employee;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM Employee WHERE id = :id";
		int result = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return result>0;
	}

}
