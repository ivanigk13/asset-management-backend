package com.lawencon.asset.service;

import com.lawencon.asset.dto.brand.DeleteBrandDtoRes;
import com.lawencon.asset.dto.brand.GetAllBrandDto;
import com.lawencon.asset.dto.brand.GetByIdBrandDto;
import com.lawencon.asset.dto.brand.InsertBrandDtoReq;
import com.lawencon.asset.dto.brand.InsertBrandDtoRes;
import com.lawencon.asset.dto.brand.UpdateBrandDtoReq;
import com.lawencon.asset.dto.brand.UpdateBrandDtoRes;

public interface BrandService {
	GetAllBrandDto getAll();
	GetByIdBrandDto getById(Long id);
	InsertBrandDtoRes insert(InsertBrandDtoReq brand);
	UpdateBrandDtoRes update(UpdateBrandDtoReq brand);
	DeleteBrandDtoRes delete(Long id);
}
