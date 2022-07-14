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

import com.lawencon.asset.dao.AssetStatusDao;
import com.lawencon.asset.dao.impl.hibernatehql.AssetStatusDaoHibernateHqlImpl;
import com.lawencon.asset.model.AssetStatus;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class AssetStatusDaoTest {

	private static AssetStatusDao assetStatusDao;
	private Long id = 1L;
	
	@BeforeAll
	public static void init(@Autowired EntityManager em) {
		AssetStatusDaoHibernateHqlImpl assetStatusDaoHibernateHqlImpl = new AssetStatusDaoHibernateHqlImpl();
		assetStatusDaoHibernateHqlImpl.setEm(em);
		assetStatusDao = assetStatusDaoHibernateHqlImpl;
	}
	
	@Transactional
	@Commit
	@Test
	@Order(1)
	void isSuccessInsertAssetStatus() throws Exception{
		AssetStatus assetStatus = new AssetStatus();
		assetStatus.setStatusName("rready");
		assetStatus.setAltName("ready to use");

		assetStatus.setCreatedBy(1L);
		assetStatus.setCreatedAt(LocalDateTime.now());
		assetStatus.setIsActive(true);
		AssetStatus result = assetStatusDao.save(assetStatus);
		assertNotNull(result);
	}
	
	@Transactional
	@Commit
	@Test
	@Order(2)
	void isSuccessUpdateAssetStatus() throws Exception{
		AssetStatus assetStatus = assetStatusDao.getById(id);
		assetStatus.setStatusName("ready");

		assetStatus.setUpdatedBy(1L);
		assetStatus.setUpdatedAt(LocalDateTime.now());
		assetStatus.setVersion(0);
		AssetStatus result = assetStatusDao.save(assetStatus);
		assertNotNull(result);
	}

	@Test
	@Order(3)
	void isSuccessGetAssetStatusById() throws Exception{
		AssetStatus assetStatus = assetStatusDao.getById(id);
		assertNotNull(assetStatus);
		assertEquals(1, assetStatus.getVersion());
	}
	
	@Test
	@Order(4)
	void isSuccessGetAll() throws Exception{
		List<AssetStatus> assetStatus = assetStatusDao.getAll();
		assertEquals(1, assetStatus.size());
	}
	
	@Transactional
	@Commit
	@Test
	@Order(5)
	void isSuccessDeleteAssetStatus() throws Exception{
		boolean result = assetStatusDao.delete(id);
		assertEquals(true, result);
	}

}
