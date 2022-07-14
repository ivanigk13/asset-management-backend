package com.lawencon.asset.service;

import com.lawencon.asset.dto.assettype.DeleteAssetTypeDtoRes;
import com.lawencon.asset.dto.assettype.GetAllAssetTypeDto;
import com.lawencon.asset.dto.assettype.GetByIdAssetTypeDto;
import com.lawencon.asset.dto.assettype.InsertAssetTypeDtoReq;
import com.lawencon.asset.dto.assettype.InsertAssetTypeDtoRes;
import com.lawencon.asset.dto.assettype.UpdateAssetTypeDtoReq;
import com.lawencon.asset.dto.assettype.UpdateAssetTypeDtoRes;

public interface AssetTypeService {
	GetAllAssetTypeDto getAll();
	GetByIdAssetTypeDto getById(Long id);
	InsertAssetTypeDtoRes insert(InsertAssetTypeDtoReq assetType);
	UpdateAssetTypeDtoRes update(UpdateAssetTypeDtoReq assetType);
	DeleteAssetTypeDtoRes delete(Long id);
}
