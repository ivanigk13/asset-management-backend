package com.lawencon.asset.dao.impl.hibernatehql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.UsersDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Roles;
import com.lawencon.asset.model.Users;

@Profile("hql")
@Repository
public class UsersDaoHibernateHqlImpl extends BaseDaoImpl implements UsersDao{

	@Override
	public List<Users> getAll() {
		String sql = "SELECT u FROM Users as u "
				+ "INNER JOIN FETCH u.role as r "
				+ "ORDER BY u.id";
		List<Users> users = em.createQuery(sql, Users.class).getResultList();
		return users;
	}

	@Override
	public Users getById(Long id) {
		Users user = em.find(Users.class, id);
		em.detach(user);		
		return user;
	}

	@Override
	public Users save(Users user) {
		if(user.getId()!=null) {
			user = em.merge(user);
			em.flush();
		}else {
			em.persist(user);
		}
		em.detach(user);
		return user;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM Users WHERE id = :id";
		int result = em.createQuery(sql)
						.setParameter("id", id)
						.executeUpdate();
		return result>0;
	}

	@Override
	public Users getByEmail(String email) {		
		String sql = "SELECT u.id, u.role.roleCode, u.username, u.password, u.email, "
				+ "u.createdBy.id, u.createdAt, u.updatedBy.id, u.updatedAt, u.version, u.isActive "
				+ "FROM Users as u WHERE u.email = :email";
		Users user = new Users();
		try { 
			Object result = em.createQuery(sql).
					setParameter("email", email).
					getSingleResult();

			Object[] obj = (Object[]) result;
			user.setId(Long.valueOf(obj[0].toString()));
			Roles role = new Roles();
			role.setRoleCode(obj[1].toString());
			user.setRole(role);
			user.setUsername(obj[2].toString());
			user.setPassword(obj[3].toString());
			user.setEmail(obj[4].toString());
			
			if(obj[5]!=null)user.setCreatedBy(Long.valueOf(obj[5].toString()));
			if(obj[6]!=null)user.setCreatedAt(((LocalDateTime)obj[6]));			
			
			if(obj[7]!=null) user.setUpdatedBy(Long.valueOf(obj[7].toString()));
			if(obj[8]!=null) user.setUpdatedAt(((LocalDateTime)obj[8]));
			
			user.setVersion(Integer.valueOf(obj[9].toString()));
			user.setIsActive(Boolean.valueOf(obj[10].toString()));
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}		
		return user;
	}

	@Override
	public List<Users> getUnemployee() {
		String sql = "SELECT u.id, u.role.id, u.username, u.password, u.email, u.createdBy.id, u.createdAt, "
				+ "u.updatedBy.id, u.updatedAt, u.version, u.isActive, u.role.roleName "
				+ "FROM Users u WHERE u.id NOT IN (SELECT user.id FROM Employee)";
		List<?> results = em.createQuery(sql).getResultList();
		List<Users> users = new ArrayList<>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			Users user = new Users();
			Roles role = new Roles();
			user.setId(Long.valueOf(obj[0].toString()));
			role.setId(Long.valueOf(obj[1].toString()));
			role.setRoleName(obj[11].toString());
			user.setRole(role);
			user.setUsername(obj[2].toString());
			user.setPassword(obj[3].toString());
			user.setEmail(obj[4].toString());
			
			if(obj[5]!=null)user.setCreatedBy(Long.valueOf(obj[5].toString()));
			if(obj[6]!=null)user.setCreatedAt(((LocalDateTime)obj[6]));			
			
			if(obj[7]!=null) user.setUpdatedBy(Long.valueOf(obj[7].toString()));
			if(obj[8]!=null) user.setUpdatedAt(((LocalDateTime)obj[8]));
			
			user.setVersion(Integer.valueOf(obj[9].toString()));
			user.setIsActive(Boolean.valueOf(obj[10].toString()));		
			users.add(user);
		});
		return users;
	}

}
