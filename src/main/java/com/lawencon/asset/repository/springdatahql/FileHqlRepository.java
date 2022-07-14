package com.lawencon.asset.repository.springdatahql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.model.File;

@Repository
public interface FileHqlRepository extends JpaRepository<File, Long>{

	@Modifying
	int removeById(Long id);
}
