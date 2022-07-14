package com.lawencon.asset.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.asset.dto.asset.DeleteAssetDtoRes;
import com.lawencon.asset.dto.asset.GetAllAssetDto;
import com.lawencon.asset.dto.asset.GetByIdAssetDto;
import com.lawencon.asset.dto.asset.GetExpiredAssetDto;
import com.lawencon.asset.dto.asset.GetReadyToUseDto;
import com.lawencon.asset.dto.asset.InsertAssetDtoRes;
import com.lawencon.asset.dto.asset.UpdateAssetDtoRes;

public interface AssetService {
	GetAllAssetDto getAll() throws IOException;
	GetByIdAssetDto getById(Long id);
	InsertAssetDtoRes insert(String data, MultipartFile file) throws IOException;
	UpdateAssetDtoRes update(String asset, MultipartFile file) throws IOException;
	DeleteAssetDtoRes delete(Long id);
	GetReadyToUseDto getReadyToUse() throws IOException;
	GetExpiredAssetDto getExpiredAsset() throws IOException;
}
