package com.lawencon.asset.dao.impl.springdatahql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.UsersDao;
import com.lawencon.asset.model.Users;
import com.lawencon.asset.repository.springdatahql.UserHqlRepository;

@Profile("jpahql")
@Repository
public class UserDaoSpringDataHqlImpl implements UsersDao{

	private UserHqlRepository userHqlRepository;
	
	@Autowired
	public void setUserHqlRepository(UserHqlRepository userHqlRepository) {
		this.userHqlRepository = userHqlRepository;
	}

	@Override
	public List<Users> getAll() {
		return userHqlRepository.findAll();
	}

	@Override
	public Users getById(Long id) {
		return userHqlRepository.getById(id);
	}

	@Override
	public Users save(Users user) {
		return userHqlRepository.saveAndFlush(user);
	}

	@Override
	public boolean delete(Long id) {
		int result = userHqlRepository.removeById(id);
		return result>0;
	}
	
	@Override
	public List<Users> getUnemployee() {		
		return userHqlRepository.getUnemployee();
	}

	@Override
	public Users getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
