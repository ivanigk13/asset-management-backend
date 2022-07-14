package com.lawencon.asset.service;

import com.lawencon.asset.dto.assetstatus.DeleteAssetStatusDtoRes;
import com.lawencon.asset.dto.assetstatus.GetAllAssetStatusDto;
import com.lawencon.asset.dto.assetstatus.GetByIdAssetStatusDto;
import com.lawencon.asset.dto.assetstatus.InsertAssetStatusDtoReq;
import com.lawencon.asset.dto.assetstatus.InsertAssetStatusDtoRes;
import com.lawencon.asset.dto.assetstatus.UpdateAssetStatusDtoReq;
import com.lawencon.asset.dto.assetstatus.UpdateAssetStatusDtoRes;

public interface AssetStatusService {
	GetAllAssetStatusDto getAll();
	GetByIdAssetStatusDto getById(Long id);
	InsertAssetStatusDtoRes insert(InsertAssetStatusDtoReq assetStatus);
	UpdateAssetStatusDtoRes update(UpdateAssetStatusDtoReq assetStatus);
	DeleteAssetStatusDtoRes delete(Long id);
}
