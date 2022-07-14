package com.lawencon.asset.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import com.lawencon.asset.dao.CompanyDao;
import com.lawencon.asset.dao.EmployeeDao;
import com.lawencon.asset.dao.RolesDao;
import com.lawencon.asset.dao.UsersDao;
import com.lawencon.asset.dao.impl.hibernatehql.CompanyDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.EmployeeDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.RolesDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.UsersDaoHibernateHqlImpl;
import com.lawencon.asset.model.Company;
import com.lawencon.asset.model.Employee;
import com.lawencon.asset.model.Roles;
import com.lawencon.asset.model.Users;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class EmployeeDaoTest {

	private static EmployeeDao employeeDao;
	private static CompanyDao companyDao;
	private static RolesDao rolesDao;
	private static UsersDao usersDao;
	
	private Long id = 1L;
	
	@BeforeAll
	public static void init(@Autowired EntityManager em) {
		EmployeeDaoHibernateHqlImpl employeeDaoHibernateHqlImpl = new EmployeeDaoHibernateHqlImpl();
		employeeDaoHibernateHqlImpl.setEm(em);
		employeeDao = employeeDaoHibernateHqlImpl;
		
		CompanyDaoHibernateHqlImpl companyDaoHibernateHqlImpl = new CompanyDaoHibernateHqlImpl();
		companyDaoHibernateHqlImpl.setEm(em);
		companyDao = companyDaoHibernateHqlImpl;
		
		RolesDaoHibernateHqlImpl rolesDaoHibernateHqlImpl = new RolesDaoHibernateHqlImpl();
		rolesDaoHibernateHqlImpl.setEm(em);
		rolesDao = rolesDaoHibernateHqlImpl;
		
		UsersDaoHibernateHqlImpl usersDaoHibernateHqlImpl = new UsersDaoHibernateHqlImpl();
		usersDaoHibernateHqlImpl.setEm(em);
		usersDao = usersDaoHibernateHqlImpl;
	}
	
	@Transactional
	@Commit
	@Test
	@Order(1)
	void isSuccessInsertEmployee() throws Exception{
		Roles roles = new Roles();
		roles.setRoleCode("SP");
		roles.setRoleName("Specta");
		roles.setCreatedBy(1L);
		roles.setCreatedAt(LocalDateTime.now());
		roles.setVersion(0);
		roles.setIsActive(true);
		roles = rolesDao.save(roles);
		
		Users user = new Users();
		user.setUsername("user6");
		user.setPassword("username6");
		user.setEmail("username6@lawencon.com");
		user.setRole(roles);
		user.setCreatedBy(1L);
		user = usersDao.save(user);
		
		Company company = new Company();
		company.setCompanyName("PT. PT");
		company.setCompanyAddress("Jl. Merdeka F");		
		company.setCreatedBy(1L);
		company.setCreatedAt(LocalDateTime.now());
		company.setIsActive(true);		
		companyDao.save(company);
		
		Employee employee = new Employee();	
		employee.setUser(user);
		employee.setCompany(company);
		
		employee.setNip("AS1111");
		employee.setEmployeeName("Mr. EE1");
		
		employee.setCreatedBy(1L);
		employee.setCreatedAt(LocalDateTime.now());
		employee.setIsActive(true);
		Employee result = employeeDao.save(employee);
		assertNotNull(result);
	}
	
	@Transactional
	@Commit
	@Test
	@Order(2)
	void isSuccessUpdateEmployee() throws Exception{
		Employee employee = employeeDao.getById(id);
		employee.setNip("EE1111");
		employee.setEmployeeName("Mr. E1");
		
		employee.setUpdatedBy(1L);
		employee.setUpdatedAt(LocalDateTime.now());
		employee.setVersion(0);
		Employee result = employeeDao.save(employee);
		assertNotNull(result);
	}

	@Transactional
	@Commit
	@Test
	@Order(3)
	void isSuccessGetEmployeeById() throws Exception{
		Employee employee = employeeDao.getById(id);
		assertNotNull(employee);
		assertEquals(1, employee.getVersion());
	}
	
	@Test
	@Order(4)
	void isSuccessGetAll() throws Exception{
		List<Employee> employee = employeeDao.getAll();
		assertEquals(1, employee.size());
	}
	
	@Transactional
	@Commit
	@Test
	@Order(5)
	void isSuccessDeleteEmployee() throws Exception{
		boolean result = employeeDao.delete(id);
		assertEquals(true, result);
	}

}
