package com.lawencon.asset.dao;

import java.util.List;

import com.lawencon.asset.model.AssetType;

public interface AssetTypeDao {
	List<AssetType> getAll();
	AssetType save(AssetType assetType);
	AssetType getById(Long id);
	boolean delete(Long id);
}
