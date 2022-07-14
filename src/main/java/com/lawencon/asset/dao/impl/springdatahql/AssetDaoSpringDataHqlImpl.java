package com.lawencon.asset.dao.impl.springdatahql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.constant.StatusList;
import com.lawencon.asset.dao.AssetDao;
import com.lawencon.asset.model.Asset;
import com.lawencon.asset.repository.springdatahql.AssetHqlRepository;

@Profile("jpahql")
@Repository
public class AssetDaoSpringDataHqlImpl implements AssetDao{

	private AssetHqlRepository assetHqlRepository;
	
	@Autowired
	public void setAssetHqlRepository(AssetHqlRepository assetHqlRepository) {
		this.assetHqlRepository = assetHqlRepository;
	}

	@Override
	public List<Asset> getAll() {
		return assetHqlRepository.findAll();
	}

	@Override
	public Asset save(Asset asset) {
		return assetHqlRepository.saveAndFlush(asset);
	}

	@Override
	public Asset getById(Long id) {
		return assetHqlRepository.getById(id);
	}

	@Override
	public boolean delete(Long id) {
		int result = assetHqlRepository.removeById(id);
		return result>0;
	}

	@Override
	public List<Asset> getReadyToUse() {
		return assetHqlRepository.getReadyToUse(StatusList.READY_TO_DEPLOY.code);
	}

	@Override
	public List<Asset> getExpiredAsset() {
		return assetHqlRepository.getExpiredAsset();
	}

}
