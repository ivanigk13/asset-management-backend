package com.lawencon.asset.dao.impl.springdatahql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.AssetTypeDao;
import com.lawencon.asset.model.AssetType;
import com.lawencon.asset.repository.springdatahql.AssetTypeHqlRepository;

@Profile("jpahql")
@Repository
public class AssetTypeDaoSpringDataHqlImpl implements AssetTypeDao{

	private AssetTypeHqlRepository assetTypeHqlRepository;
	
	@Autowired
	public void setAssetTypeHqlRepository(AssetTypeHqlRepository assetTypeHqlRepository) {
		this.assetTypeHqlRepository = assetTypeHqlRepository;
	}

	@Override
	public List<AssetType> getAll() {
		return assetTypeHqlRepository.findAll();
	}

	@Override
	public AssetType save(AssetType assetType) {
		return assetTypeHqlRepository.saveAndFlush(assetType);
	}

	@Override
	public AssetType getById(Long id) {
		return assetTypeHqlRepository.getById(id);
	}

	@Override
	public boolean delete(Long id) {
		int result = assetTypeHqlRepository.removeById(id);
		return result>0;
	}

}
