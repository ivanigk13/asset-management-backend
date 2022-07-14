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

import com.lawencon.asset.dao.BrandDao;
import com.lawencon.asset.dao.impl.hibernatehql.BrandDaoHibernateHqlImpl;
import com.lawencon.asset.model.Brand;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class BrandDaoTest {

	private static BrandDao brandDao;
	private Long id = 1L;
	
	@BeforeAll
	public static void init(@Autowired EntityManager em) {
		BrandDaoHibernateHqlImpl brandDaoHibernateHqlImpl = new BrandDaoHibernateHqlImpl();
		brandDaoHibernateHqlImpl.setEm(em);
		brandDao = brandDaoHibernateHqlImpl;
	}
	
	@Transactional
	@Commit
	@Test
	@Order(1)
	void isSuccessInsertBrand() throws Exception{
		Brand brand = new Brand();
		brand.setBrandName("Super 6");

		brand.setCreatedBy(1L);
		brand.setCreatedAt(LocalDateTime.now());
		brand.setVersion(0);
		brand.setIsActive(true);
		Brand result = brandDao.save(brand);
		assertNotNull(result);
	}
	
	@Transactional
	@Commit
	@Test
	@Order(2)
	void isSuccessUpdateBrand() throws Exception{
		Brand brand = brandDao.getById(id);
		brand.setBrandName("Super 66");

		brand.setUpdatedBy(1L);
		brand.setUpdatedAt(LocalDateTime.now());
		brand.setVersion(0);
		Brand result = brandDao.save(brand);
		assertNotNull(result);
	}

	@Test
	@Order(3)
	void isSuccessGetBrandById() throws Exception{
		Brand brand = brandDao.getById(id);
		assertNotNull(brand);
		assertEquals(1, brand.getVersion());
	}
	
	@Test
	@Order(4)
	void isSuccessGetAll() throws Exception{
		List<Brand> brand = brandDao.getAll();
		assertEquals(1, brand.size());
	}
	
	@Transactional
	@Commit
	@Test
	@Order(5)
	void isSuccessDeleteBrand() throws Exception{
		boolean result = brandDao.delete(id);
		assertEquals(true, result);
	}

}
