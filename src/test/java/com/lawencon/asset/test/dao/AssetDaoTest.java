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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.lawencon.asset.dao.AssetDao;
import com.lawencon.asset.dao.AssetStatusDao;
import com.lawencon.asset.dao.AssetTypeDao;
import com.lawencon.asset.dao.BrandDao;
import com.lawencon.asset.dao.CompanyDao;
import com.lawencon.asset.dao.impl.hibernatehql.AssetDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.AssetStatusDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.AssetTypeDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.BrandDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.CompanyDaoHibernateHqlImpl;
import com.lawencon.asset.model.Asset;
import com.lawencon.asset.model.AssetStatus;
import com.lawencon.asset.model.AssetType;
import com.lawencon.asset.model.Brand;
import com.lawencon.asset.model.Company;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class AssetDaoTest {

	private static AssetDao assetDao;
	private static CompanyDao companyDao;
	private static BrandDao brandDao;
	private static AssetTypeDao assetTypeDao;
	private static AssetStatusDao assetStatusDao;
	private Long id = 1L;
	
	@BeforeAll
	public static void init(@Autowired EntityManager em) {
		AssetDaoHibernateHqlImpl assetDaoHibernateHqlImpl = new AssetDaoHibernateHqlImpl();
		assetDaoHibernateHqlImpl.setEm(em);
		assetDao = assetDaoHibernateHqlImpl;
		
		CompanyDaoHibernateHqlImpl companyDaoHibernateHqlImpl = new CompanyDaoHibernateHqlImpl();
		companyDaoHibernateHqlImpl.setEm(em);
		companyDao = companyDaoHibernateHqlImpl;
		
		AssetTypeDaoHibernateHqlImpl assetTypeDaoHibernateHqlImpl = new AssetTypeDaoHibernateHqlImpl();
		assetTypeDaoHibernateHqlImpl.setEm(em);
		assetTypeDao = assetTypeDaoHibernateHqlImpl;
		
		AssetStatusDaoHibernateHqlImpl assetStatusDaoHibernateHqlImpl = new AssetStatusDaoHibernateHqlImpl();
		assetStatusDaoHibernateHqlImpl.setEm(em);
		assetStatusDao = assetStatusDaoHibernateHqlImpl;
		
		BrandDaoHibernateHqlImpl brandDaoHibernateHqlImpl = new BrandDaoHibernateHqlImpl();
		brandDaoHibernateHqlImpl.setEm(em);
		brandDao = brandDaoHibernateHqlImpl;
	}
	
	@Transactional
	@Commit
	@Test
	@Order(1)
	void isSuccessInsertAsset() throws Exception{
		Asset asset = new Asset();
		
		Company company = new Company();
		company.setCompanyName("PT. PT");
		company.setCompanyAddress("Jl. Merdeka F");		
		company.setCreatedBy(1L);
		company.setCreatedAt(LocalDateTime.now());
		company.setIsActive(true);		
		company = companyDao.save(company);
		
		AssetType assetType = new AssetType();
		assetType.setTypeCode("code");
		assetType.setTypeName("asfff");		
		assetType.setCreatedBy(1L);
		assetType.setCreatedAt(LocalDateTime.now());
		assetType.setIsActive(true);
		assetType = assetTypeDao.save(assetType);
		
		AssetStatus assetStatus = new AssetStatus();
		assetStatus.setStatusName("rready");
		assetStatus.setAltName("ready to use");
		assetStatus.setCreatedBy(1L);
		assetStatus.setCreatedAt(LocalDateTime.now());
		assetStatus.setIsActive(true);
		assetStatus = assetStatusDao.save(assetStatus);
		
		Brand brand = new Brand();
		brand.setBrandName("Super 6");
		brand.setCreatedBy(1L);
		brand.setCreatedAt(LocalDateTime.now());
		brand.setIsActive(true);
		brand = brandDao.save(brand);
				
		asset.setCompany(company);
		asset.setAssetType(assetType);
		asset.setAssetStatus(assetStatus);
		asset.setBrand(brand);
		asset.setAssetName("asset 6");
		asset.setSerial("as1111");
		asset.setAssetDescription("asset 6");
		asset.setInvoice("2022");
		
		asset.setCreatedBy(1L);
		asset.setCreatedAt(LocalDateTime.now());
		asset.setIsActive(true);
		
		Asset result = assetDao.save(asset);
		assertNotNull(result);
	}
	
	@Transactional
	@Commit
	@Test
	@Order(2)
	void isSuccessUpdateAsset() throws Exception{
		Asset asset = assetDao.getById(id);
		asset.setAssetName("Asset 6");
		asset.setSerial("AS1111");
		asset.setAssetDescription("Asset Description 6");

		asset.setUpdatedBy(1L);
		asset.setUpdatedAt(LocalDateTime.now());
		asset.setVersion(0);
		Asset result = assetDao.save(asset);
		assertNotNull(result);
	}

	@Test
	@Order(3)
	void isSuccessGetAssetById() throws Exception{
		Asset asset = assetDao.getById(id);
		assertNotNull(asset);
		assertEquals(1, asset.getVersion());
	}
	
	@Test
	@Order(4)
	void isSuccessGetAll() throws Exception{
		List<Asset> asset = assetDao.getAll();
		assertEquals(1, asset.size());
	}
	
	@Transactional
	@Commit
	@Test
	@Order(5)
	void isSuccessDeleteAsset() throws Exception{
		boolean result = assetDao.delete(id);
		assertEquals(true, result);
	}

}
