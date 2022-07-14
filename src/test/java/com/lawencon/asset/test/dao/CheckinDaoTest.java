package com.lawencon.asset.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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

import com.lawencon.asset.dao.AssetDao;
import com.lawencon.asset.dao.AssetStatusDao;
import com.lawencon.asset.dao.AssetTypeDao;
import com.lawencon.asset.dao.BrandDao;
import com.lawencon.asset.dao.CheckinDao;
import com.lawencon.asset.dao.CheckoutDao;
import com.lawencon.asset.dao.CompanyDao;
import com.lawencon.asset.dao.EmployeeDao;
import com.lawencon.asset.dao.RolesDao;
import com.lawencon.asset.dao.UsersDao;
import com.lawencon.asset.dao.impl.hibernatehql.AssetDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.AssetStatusDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.AssetTypeDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.BrandDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.CheckinDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.CheckoutDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.CompanyDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.EmployeeDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.RolesDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.UsersDaoHibernateHqlImpl;
import com.lawencon.asset.model.Asset;
import com.lawencon.asset.model.AssetStatus;
import com.lawencon.asset.model.AssetType;
import com.lawencon.asset.model.Brand;
import com.lawencon.asset.model.Checkin;
import com.lawencon.asset.model.Checkout;
import com.lawencon.asset.model.Company;
import com.lawencon.asset.model.Employee;
import com.lawencon.asset.model.Roles;
import com.lawencon.asset.model.Users;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class CheckinDaoTest {

	private static CheckinDao checkinDao;
	private static CheckoutDao checkoutDao;
	private static AssetDao assetDao;
	private static CompanyDao companyDao;
	private static BrandDao brandDao;
	private static AssetTypeDao assetTypeDao;
	private static AssetStatusDao assetStatusDao;
	private static EmployeeDao employeeDao;
	private static RolesDao rolesDao;
	private static UsersDao usersDao;
	private Long id = 1L;
	
	@BeforeAll
	public static void init(@Autowired EntityManager em) {
		CheckinDaoHibernateHqlImpl checkinDaoHibernateHqlImpl = new CheckinDaoHibernateHqlImpl();
		checkinDaoHibernateHqlImpl.setEm(em);
		checkinDao = checkinDaoHibernateHqlImpl;
		
		CheckoutDaoHibernateHqlImpl checkoutDaoHibernateHqlImpl = new CheckoutDaoHibernateHqlImpl();
		checkoutDaoHibernateHqlImpl.setEm(em);
		checkoutDao = checkoutDaoHibernateHqlImpl;
		
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
		
		EmployeeDaoHibernateHqlImpl employeeDaoHibernateHqlImpl = new EmployeeDaoHibernateHqlImpl();
		employeeDaoHibernateHqlImpl.setEm(em);
		employeeDao = employeeDaoHibernateHqlImpl;
		
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
	void isSuccessInsertCheckin() throws Exception{
		Checkin checkin = new Checkin();
		
		Checkout checkout = new Checkout();
		
		Asset asset = new Asset();
		
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
		company = companyDao.save(company);
		
		Employee employee = new Employee();	
		employee.setUser(user);
		employee.setCompany(company);
		
		employee.setNip("AS1111");
		employee.setEmployeeName("Mr. EE1");
		
		employee.setCreatedBy(1L);
		employee.setCreatedAt(LocalDateTime.now());
		employee.setIsActive(true);
		employee = employeeDao.save(employee);
		
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
		
		asset = assetDao.save(asset);
		
		checkout.setAsset(asset);
		checkout.setEmployee(employee);
		checkout.setOtherAsset(null);
		checkout.setPlace(null);
		checkout.setExpired(LocalDate.now());

		checkout.setCreatedBy(1L);
		checkout.setCreatedAt(LocalDateTime.now());
		checkout.setIsActive(true);
		checkout = checkoutDao.save(checkout);

		checkin.setAssetStatus(assetStatus);
		checkin.setCheckout(checkout);
		
		checkin.setCreatedBy(1L);
		checkin.setCreatedAt(LocalDateTime.now());
		checkin.setVersion(0);
		checkin.setIsActive(true);
		Checkin result = checkinDao.save(checkin);
		assertNotNull(result);
	}

	@Test
	@Order(2)
	void isSuccessGetCheckinById() throws Exception{
		Checkin checkin = checkinDao.getById(id);
		assertNotNull(checkin);
	}
	
	@Test
	@Order(3)
	void isSuccessGetAll() throws Exception{
		List<Checkin> checkin = checkinDao.getAll();
		assertEquals(1, checkin.size());
	}
	

}
