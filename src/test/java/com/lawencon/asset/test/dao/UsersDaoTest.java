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

import com.lawencon.asset.dao.RolesDao;
import com.lawencon.asset.dao.UsersDao;
import com.lawencon.asset.dao.impl.hibernatehql.RolesDaoHibernateHqlImpl;
import com.lawencon.asset.dao.impl.hibernatehql.UsersDaoHibernateHqlImpl;
import com.lawencon.asset.model.Roles;
import com.lawencon.asset.model.Users;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class UsersDaoTest {

	private static UsersDao usersDao;
	private static RolesDao rolesDao;
	private Long id = 1L;
	
	@BeforeAll
	public static void init(@Autowired EntityManager em) {
		UsersDaoHibernateHqlImpl usersDaoHibernateHqlImpl = new UsersDaoHibernateHqlImpl();
		usersDaoHibernateHqlImpl.setEm(em);
		usersDao = usersDaoHibernateHqlImpl;
		
		RolesDaoHibernateHqlImpl daoHibernateHqlImpl = new RolesDaoHibernateHqlImpl();
		daoHibernateHqlImpl.setEm(em);
		rolesDao = daoHibernateHqlImpl;
	}
	
	@Transactional
	@Commit
	@Test
	@Order(1)
	void isSuccessInsertUser() throws Exception{
		Roles roles = new Roles();
		roles.setRoleCode("SP");
		roles.setRoleName("Specta");
		roles.setCreatedBy(1L);
		roles.setCreatedAt(LocalDateTime.now());
		roles.setIsActive(true);
		rolesDao.save(roles);
		
		Users user = new Users();
		user.setUsername("user6");
		user.setPassword("username6");
		user.setEmail("username6@lawencon.com");
		user.setRole(roles);
		
		user.setCreatedBy(1L);
		user.setIsActive(true);
		Users result = usersDao.save(user);
		assertNotNull(result);
	}
	
	@Transactional
	@Commit
	@Test
	@Order(2)
	void isSuccessUpdateUser() throws Exception{
		Users user = usersDao.getById(id);
		user.setUsername("username6");
		user.setUpdatedBy(1L);
		user.setVersion(0);
		Users result = usersDao.save(user);
		assertNotNull(result);
	}

	@Test
	@Order(3)
	void isSuccessGetUserById() throws Exception{
		Users user = usersDao.getById(id);
		assertNotNull(user);
		assertEquals(1, user.getVersion());
	}
	
	@Test
	@Order(4)
	void isSuccessGetAll() throws Exception{
		List<Users> users = usersDao.getAll();
		assertEquals(1, users.size());
	}
	
	@Transactional
	@Commit
	@Test
	@Order(5)
	void isSuccessDeleteUser() throws Exception{
		boolean result = usersDao.delete(id);
		assertEquals(true, result);
	}

}
