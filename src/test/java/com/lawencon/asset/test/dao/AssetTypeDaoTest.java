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

import com.lawencon.asset.dao.AssetTypeDao;
import com.lawencon.asset.dao.impl.hibernatehql.AssetTypeDaoHibernateHqlImpl;
import com.lawencon.asset.model.AssetType;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class AssetTypeDaoTest {

	private static AssetTypeDao assetTypeDao;
	private Long id = 1L;
	
	@BeforeAll
	public static void init(@Autowired EntityManager em) {
		AssetTypeDaoHibernateHqlImpl assetTypeDaoHibernateHqlImpl = new AssetTypeDaoHibernateHqlImpl();
		assetTypeDaoHibernateHqlImpl.setEm(em);
		assetTypeDao = assetTypeDaoHibernateHqlImpl;
	}
	
	@Transactional
	@Commit
	@Test
	@Order(1)
	void isSuccessInsertAssetType() throws Exception{
		AssetType assetType = new AssetType();
		assetType.setTypeCode("code");
		assetType.setTypeName("asfff");
		
		assetType.setCreatedBy(1L);
		assetType.setCreatedAt(LocalDateTime.now());
		assetType.setIsActive(true);
		AssetType result = assetTypeDao.save(assetType);
		assertNotNull(result);
	}
	
	@Transactional
	@Commit
	@Test
	@Order(2)
	void isSuccessUpdateAssetType() throws Exception{
		AssetType assetType = assetTypeDao.getById(id);
		assetType.setTypeName("breakable");

		assetType.setUpdatedBy(1L);
		assetType.setUpdatedAt(LocalDateTime.now());
		assetType.setVersion(0);
		AssetType result = assetTypeDao.save(assetType);
		assertNotNull(result);
	}

	@Test
	@Order(3)
	void isSuccessGetAssetTypeById() throws Exception{
		AssetType assetType = assetTypeDao.getById(id);
		assertNotNull(assetType);
		assertEquals(1, assetType.getVersion());
	}
	
	@Test
	@Order(4)
	void isSuccessGetAll() throws Exception{
		List<AssetType> assetType = assetTypeDao.getAll();
		assertEquals(1, assetType.size());
	}
	
	@Transactional
	@Commit
	@Test
	@Order(5)
	void isSuccessDeleteAssetType() throws Exception{
		boolean result = assetTypeDao.delete(id);
		assertEquals(true, result);
	}

}
