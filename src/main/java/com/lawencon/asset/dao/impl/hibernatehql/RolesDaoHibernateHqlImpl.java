package com.lawencon.asset.dao.impl.hibernatehql;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.RolesDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Roles;

@Profile("hql")
@Repository
public class RolesDaoHibernateHqlImpl extends BaseDaoImpl implements RolesDao{

	@Override
	public List<Roles> getAll() {
		String sql = "SELECT r FROM Roles r "
				+ "ORDER BY r.id";
		List<Roles> roles = em.createQuery(sql, Roles.class).getResultList();
		return roles;
	}

	@Transactional(rollbackOn = Exception.class)
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
		String sql = "DELETE FROM Roles WHERE id = :id";
		int result = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return result > 0;
	}

}
