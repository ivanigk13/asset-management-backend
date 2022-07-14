package com.lawencon.asset.dao.impl.hibernatenative;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.RolesDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Roles;

@Profile("native")
@Repository
public class RolesDaoHibernateNativeImpl extends BaseDaoImpl implements RolesDao{

	@Override
	public List<Roles> getAll() {
		String sql = "SELECT id, role_code, role_name, created_by, created_at, updated_by, updated_at, version, is_active "
				+ "FROM roles";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<Roles> roles = new ArrayList<Roles>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			Roles role = new Roles();
			role.setId(Long.valueOf(obj[0].toString()));
			role.setRoleCode(obj[1].toString());
			role.setRoleName(obj[2].toString());
			
			if(obj[3]!=null)role.setCreatedBy(Long.valueOf(obj[3].toString()));
			if(obj[4]!=null)role.setCreatedAt(Timestamp.valueOf(obj[4].toString()).toLocalDateTime());			
			
			if(obj[5]!=null) role.setUpdatedBy(Long.valueOf(obj[5].toString()));
			if(obj[6]!=null) role.setUpdatedAt(Timestamp.valueOf(obj[6].toString()).toLocalDateTime());
			
			role.setVersion(Integer.valueOf(obj[7].toString()));
			role.setIsActive(Boolean.valueOf(obj[8].toString()));	
			roles.add(role);
		});
		return roles;
	}

	@Override
	public Roles save(Roles role) {		
		if (role.getId() != null) {
			role = em.merge(role);
			em.flush();
		} else {
			em.persist(role);
		}
		em.detach(role);
		return role;
	}

	@Override
	public Roles getById(Long id) {
		Roles role = em.find(Roles.class, id);
		em.detach(role);
		return role;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM roles WHERE id = :id";
		int result = em.createNativeQuery(sql)
						.setParameter("id", id)
						.executeUpdate();
		return result>0;
	}

}
