package com.lawencon.asset.repository.springdatahql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.model.Users;

@Repository
public interface UserHqlRepository extends JpaRepository<Users, Long>{

	@Query(value="FROM Users u WHERE u.id NOT IN (SELECT user.id FROM Employee)")
	List<Users> getUnemployee();
	
	@Modifying
	int removeById(Long id);
}
