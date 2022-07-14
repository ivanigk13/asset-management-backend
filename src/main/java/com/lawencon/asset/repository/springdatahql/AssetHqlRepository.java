package com.lawencon.asset.repository.springdatahql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.model.Asset;

@Repository
public interface AssetHqlRepository extends JpaRepository<Asset, Long>{

	@Query(value="SELECT a FROM Asset a "
			+ "WHERE a.assetStatus.id = (SELECT id FROM AssetStatus WHERE statusCode = :statusCode) "
			+ "ORDER BY a.id ASC")
	List<Asset> getReadyToUse(@Param("statusCode") String statusCode);
	
	@Query(value="FROM Asset WHERE expired <= CURRENT_TIMESTAMP ORDER BY id ASC")
	List<Asset> getExpiredAsset();
	
	@Modifying
	int removeById(Long id);
}
