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
import com.lawencon.asset.dao.impl.hibernatehql.RolesDaoHibernateHqlImpl;
import com.lawencon.asset.model.Roles;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class RolesDaoTest {

	private static RolesDao rolesDao;
	private Long id = 1L;

	@BeforeAll
	public static void init(@Autowired EntityManager em) {
		RolesDaoHibernateHqlImpl rolesDaoHibernateHqlImpl = new RolesDaoHibernateHqlImpl();
		rolesDaoHibernateHqlImpl.setEm(em);
		rolesDao = rolesDaoHibernateHqlImpl;
	}

	@Transactional
	@Commit
	@Test
	@Order(1)
	void isSuccessInsertRole() {
		Roles roles = new Roles();
		roles.setRoleCode("SP");
		roles.setRoleName("Specta");
		roles.setCreatedBy(1L);
		roles.setCreatedAt(LocalDateTime.now());
		roles.setIsActive(true);
		Roles result = rolesDao.save(roles);
		assertNotNull(result);
	}

	@Transactional
	@Commit
	@Test
	@Order(2)
	void isSuccessUpdateRole() {
		Roles roles = rolesDao.getById(id);
		roles.setRoleName("Spectator");
		roles.setUpdatedBy(1L);
		roles.setUpdatedAt(LocalDateTime.now());
		roles.setVersion(0);
		Roles result = rolesDao.save(roles);
		assertNotNull(result);
	}

	@Test
	@Order(3)
	void isSuccessGetRoleById() {
		Roles roles = rolesDao.getById(id);
		assertNotNull(roles);
		assertEquals(1, roles.getVersion());
	}

	@Test
	@Order(4)
	void isSuccessGetAll() {
		List<Roles> roles = rolesDao.getAll();
		assertEquals(1, roles.size());
	}

	@Transactional
	@Test
	@Order(5)
	void isSuccessDeleteRole() {
		boolean result = rolesDao.delete(id);
		assertEquals(true, result);
	}

}
