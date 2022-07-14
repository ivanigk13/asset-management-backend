package com.lawencon.asset.dao;

import java.util.List;

import com.lawencon.asset.model.Users;

public interface UsersDao {
	List<Users> getAll();
	Users getById(Long id);
	Users save(Users user);
	boolean delete(Long id);
	Users getByEmail(String email);
	List<Users> getUnemployee();
}
