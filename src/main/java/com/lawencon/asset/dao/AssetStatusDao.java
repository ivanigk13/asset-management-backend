package com.lawencon.asset.dao;

import java.util.List;

import com.lawencon.asset.model.AssetStatus;

public interface AssetStatusDao {
	List<AssetStatus> getAll();
	AssetStatus save(AssetStatus assetStatus);
	AssetStatus getById(Long id);
	boolean delete(Long id);
}
