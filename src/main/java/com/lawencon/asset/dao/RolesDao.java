package com.lawencon.asset.dao;

import java.util.List;

import com.lawencon.asset.model.Roles;

public interface RolesDao {
	List<Roles> getAll();
	Roles save(Roles role);
	Roles getById(Long id);
	boolean delete(Long id);
}
