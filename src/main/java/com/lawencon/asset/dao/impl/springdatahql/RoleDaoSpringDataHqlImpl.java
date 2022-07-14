package com.lawencon.asset.dao.impl.springdatahql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.RolesDao;
import com.lawencon.asset.model.Roles;
import com.lawencon.asset.repository.springdatahql.RoleHqlRepository;

@Profile("jpahql")
@Repository
public class RoleDaoSpringDataHqlImpl implements RolesDao{

	private RoleHqlRepository roleRepository;
	
	@Autowired
	public void setRoleRepository(RoleHqlRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<Roles> getAll() {
		return roleRepository.findAll();
	}

	@Override
	public Roles save(Roles role) {
		return roleRepository.saveAndFlush(role);
	}

	@Override
	public Roles getById(Long id) {
		return roleRepository.getById(id);
	}

	@Override
	public boolean delete(Long id) {
		int result = roleRepository.removeById(id);
		return result>0;
	}
	
}
