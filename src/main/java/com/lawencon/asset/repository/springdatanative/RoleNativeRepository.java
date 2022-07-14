package com.lawencon.asset.repository.springdatanative;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.model.Roles;

@Repository
public interface RoleNativeRepository extends JpaRepository<Roles, Long>{

	@Query(value="SELECT id, role_code, role_name FROM roles WHERE role_code = :code", nativeQuery = true)
	List<Object[]> findByCode(@Param("code") String code);
	
	@Modifying
	@Query(value="DELETE FROM Roles WHERE roleCode = :code")
	int deleteByCode(@Param("code") String code);
}
