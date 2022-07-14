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
import com.lawencon.asset.dao.impl.hibernatehql.CompanyDaoHibernateHqlImpl;
import com.lawencon.asset.model.Company;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class CompanyDaoTest {

	private static CompanyDao companyDao;
	private Long id = 1L;
	
	@BeforeAll
	public static void init(@Autowired EntityManager em) {
		CompanyDaoHibernateHqlImpl companyDaoHibernateHqlImpl = new CompanyDaoHibernateHqlImpl();
		companyDaoHibernateHqlImpl.setEm(em);
		companyDao = companyDaoHibernateHqlImpl;
	}
	
	@Transactional
	@Commit
	@Test
	@Order(1)
	void isSuccessInsertCompany() throws Exception{
		Company company = new Company();
		company.setCompanyName("PT. PT");
		company.setCompanyAddress("Jl. Merdeka F");
		
		company.setCreatedBy(1L);
		company.setCreatedAt(LocalDateTime.now());
		company.setIsActive(true);
		
		Company result = companyDao.save(company);
		assertNotNull(result);
	}
	
	@Transactional
	@Commit
	@Test
	@Order(2)
	void isSuccessUpdateCompany() throws Exception{
		Company company = companyDao.getById(id);
		company.setCompanyName("PT. F");

		company.setUpdatedBy(1L);
		company.setUpdatedAt(LocalDateTime.now());
		company.setVersion(0);

		Company result = companyDao.save(company);
		assertNotNull(result);
	}

	@Test
	@Order(3)
	void isSuccessGetCompanyById() throws Exception{
		Company company = companyDao.getById(id);
		assertNotNull(company);
		assertEquals(1, company.getVersion());
	}
	
	@Test
	@Order(4)
	void isSuccessGetAll() throws Exception{
		List<Company> company = companyDao.getAll();
		assertEquals(1, company.size());
	}
	
	@Transactional
	@Commit
	@Test
	@Order(5)
	void isSuccessDeleteCompany() throws Exception{
		boolean result = companyDao.delete(id);
		assertEquals(true, result);
	}

}
