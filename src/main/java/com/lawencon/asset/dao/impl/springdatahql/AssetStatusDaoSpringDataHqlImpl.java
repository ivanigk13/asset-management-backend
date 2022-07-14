package com.lawencon.asset.dao.impl.springdatahql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.AssetStatusDao;
import com.lawencon.asset.model.AssetStatus;
import com.lawencon.asset.repository.springdatahql.AssetStatusHqlRepository;

@Profile("jpahql")
@Repository
public class AssetStatusDaoSpringDataHqlImpl implements AssetStatusDao{

	private AssetStatusHqlRepository assetStatusHqlRepository;
	
	@Autowired
	public void setAssetStatusHqlRepository(AssetStatusHqlRepository assetStatusHqlRepository) {
		this.assetStatusHqlRepository = assetStatusHqlRepository;
	}

	@Override
	public List<AssetStatus> getAll() {
		return assetStatusHqlRepository.findAll();
	}

	@Override
	public AssetStatus save(AssetStatus assetStatus) {
		return assetStatusHqlRepository.saveAndFlush(assetStatus);
	}

	@Override
	public AssetStatus getById(Long id) {
		return assetStatusHqlRepository.getById(id);
	}

	@Override
	public boolean delete(Long id) {
		int result = assetStatusHqlRepository.removeById(id);
		return result>0;
	}

}
