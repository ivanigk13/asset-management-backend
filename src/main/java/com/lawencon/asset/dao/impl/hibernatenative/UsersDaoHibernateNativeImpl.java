package com.lawencon.asset.dao.impl.hibernatenative;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.UsersDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Roles;
import com.lawencon.asset.model.Users;

@Profile("native")
@Repository
public class UsersDaoHibernateNativeImpl extends BaseDaoImpl implements UsersDao{

	@Override
	public List<Users> getAll() {
		String sql = "SELECT u.id, u.role_id, u.username, u.password, u.email, u.created_by, u.created_at, "
				+ "u.updated_by, u.updated_at, u.version, u.is_active, r.role_name "
				+ "FROM users u JOIN roles r ON u.role_id = r.id "
				+ "ORDER BY u.id";
		List<?> results = em.createNativeQuery(sql).getResultList();
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
		String sql = "DELETE FROM users WHERE id = :id";
		int result = em.createNativeQuery(sql)
						.setParameter("id", id)
						.executeUpdate();
		return result>0;
	}

	@Override
	public List<Users> getUnemployee() {
		String sql = "SELECT u.id, u.role_id, u.username, u.password, u.email, u.created_by, u.created_at, u.updated_by, "
				+ "u.updated_at, u.version, u.is_active, r.role_name "
				+ "FROM users u JOIN roles r ON u.role_id = r.id "
				+ "WHERE u.id NOT IN (SELECT user_id FROM employee)";
		List<?> results = em.createNativeQuery(sql).getResultList();
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
			if(obj[6]!=null)user.setCreatedAt(Timestamp.valueOf(obj[6].toString()).toLocalDateTime());			
			
			if(obj[7]!=null) user.setUpdatedBy(Long.valueOf(obj[7].toString()));
			if(obj[8]!=null) user.setUpdatedAt(Timestamp.valueOf(obj[8].toString()).toLocalDateTime());
			
			user.setVersion(Integer.valueOf(obj[9].toString()));
			user.setIsActive(Boolean.valueOf(obj[10].toString()));
			users.add(user);
		});
		return users;
	}

	@Override
	public Users getByEmail(String email) {
		String sql = "SELECT u.id as userId, u.role_id, r.role_code, u.username, u.password, u.email, u.created_by, u.created_at, u.updated_by, "
				+ "u.updated_at, u.version, u.is_active "
				+ "FROM users u "
				+ "JOIN roles r ON u.role_id = r.id "
				+ "WHERE u.email = :email";
		Object result = em.createNativeQuery(sql)
							.setParameter("email", email)
							.getSingleResult();
		Users user = new Users();
		Object[] obj = (Object[]) result;
		user.setId(Long.valueOf(obj[0].toString()));
		Roles role = new Roles();
		role.setId(Long.valueOf(obj[1].toString()));
		role.setRoleCode(obj[2].toString());
		user.setRole(role);
		user.setUsername(obj[3].toString());
		user.setPassword(obj[4].toString());
		user.setEmail(obj[5].toString());
		
		if(obj[6]!=null)user.setCreatedBy(Long.valueOf(obj[6].toString()));
		if(obj[7]!=null)user.setCreatedAt(Timestamp.valueOf(obj[7].toString()).toLocalDateTime());			
		
		if(obj[8]!=null) user.setUpdatedBy(Long.valueOf(obj[8].toString()));
		if(obj[9]!=null) user.setUpdatedAt(Timestamp.valueOf(obj[9].toString()).toLocalDateTime());
		
		user.setVersion(Integer.valueOf(obj[10].toString()));
		user.setIsActive(Boolean.valueOf(obj[11].toString()));
		return user;
	}

}
