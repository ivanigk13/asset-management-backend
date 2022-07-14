package com.lawencon.asset.dao;

import java.io.IOException;
import java.util.List;

import com.lawencon.asset.model.Asset;

public interface AssetDao {	
	List<Asset> getAll() throws IOException;
	Asset save(Asset asset);
	Asset getById(Long id);
	boolean delete(Long id);
	List<Asset> getReadyToUse() throws IOException;
	List<Asset> getExpiredAsset() throws IOException;
}
