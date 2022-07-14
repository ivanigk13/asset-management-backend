package com.lawencon.asset.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lawencon.asset.dao.BrandDao;
import com.lawencon.asset.dto.brand.BrandModelDto;
import com.lawencon.asset.dto.brand.DeleteBrandDtoRes;
import com.lawencon.asset.dto.brand.GetAllBrandDto;
import com.lawencon.asset.dto.brand.GetByIdBrandDto;
import com.lawencon.asset.dto.brand.InsertBrandDtoData;
import com.lawencon.asset.dto.brand.InsertBrandDtoReq;
import com.lawencon.asset.dto.brand.InsertBrandDtoRes;
import com.lawencon.asset.dto.brand.UpdateBrandDtoData;
import com.lawencon.asset.dto.brand.UpdateBrandDtoReq;
import com.lawencon.asset.dto.brand.UpdateBrandDtoRes;
import com.lawencon.asset.model.Brand;
import com.lawencon.asset.service.BrandService;

@Service
public class BrandServiceImpl extends BaseServiceImpl implements BrandService{
	
	private BrandDao brandDao;
	
	@Autowired
	public BrandServiceImpl(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	@Override
	public GetAllBrandDto getAll() {
		GetAllBrandDto allBrandDto = new GetAllBrandDto();
		List<BrandModelDto> datas = new ArrayList<BrandModelDto>();
		List<Brand> brands = brandDao.getAll();
		for (Brand brand : brands) {
			BrandModelDto data = new BrandModelDto();
			data.setId(brand.getId());
			data.setBrandName(brand.getBrandName());
			data.setVersion(brand.getVersion());
			data.setIsActive(brand.getIsActive());
			datas.add(data);
		}
		allBrandDto.setData(datas);
		return allBrandDto;
	}

	@Override
	public GetByIdBrandDto getById(Long id) {
		GetByIdBrandDto brandDto = new GetByIdBrandDto();
		BrandModelDto data = new BrandModelDto();
		Brand brand = brandDao.getById(id);
		data.setId(brand.getId());
		data.setBrandName(brand.getBrandName());
		data.setVersion(brand.getVersion());
		data.setIsActive(brand.getIsActive());
		brandDto.setData(data);
		return brandDto;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertBrandDtoRes insert(InsertBrandDtoReq brand) {
		InsertBrandDtoRes brandDtoRes = new InsertBrandDtoRes();
		InsertBrandDtoData data = new InsertBrandDtoData();
		Brand brandInsert = new Brand();
		brandInsert.setBrandName(brand.getBrandName());
		brandInsert.setCreatedBy(getUserId());
		Brand newBrand = brandDao.save(brandInsert);
		if(newBrand!=null) {
			data.setId(newBrand.getId());
			brandDtoRes.setData(data);
			brandDtoRes.setMsg("berhasil insert brand");
		}
		return brandDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateBrandDtoRes update(UpdateBrandDtoReq brand) {
		UpdateBrandDtoRes brandDtoRes = new UpdateBrandDtoRes();
		UpdateBrandDtoData data = new UpdateBrandDtoData();
		
		Brand brandUpdate = brandDao.getById(brand.getId());
		brandUpdate.setId(brand.getId());
		brandUpdate.setVersion(brand.getVersion());
		brandUpdate.setBrandName(brand.getBrandName());
		brandUpdate.setUpdatedBy(getUserId());
		Brand newBrand = brandDao.save(brandUpdate);
		if(newBrand!=null) {
			data.setVersion(newBrand.getVersion());
			brandDtoRes.setData(data);
			brandDtoRes.setMsg("berhasil update brand");
		}
		return brandDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteBrandDtoRes delete(Long id) {
		DeleteBrandDtoRes deleteBrandDtoRes = new DeleteBrandDtoRes();
		Boolean result = brandDao.delete(id);
		if(result) {
			deleteBrandDtoRes.setMsg("Berhasil delete");
			return deleteBrandDtoRes;
		}
		throw new DataIntegrityViolationException("Gagal delete");
	}

}
