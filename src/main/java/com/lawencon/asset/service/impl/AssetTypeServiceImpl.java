package com.lawencon.asset.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.asset.dao.AssetTypeDao;
import com.lawencon.asset.dto.assettype.AssetTypeModelDto;
import com.lawencon.asset.dto.assettype.DeleteAssetTypeDtoRes;
import com.lawencon.asset.dto.assettype.GetAllAssetTypeDto;
import com.lawencon.asset.dto.assettype.GetByIdAssetTypeDto;
import com.lawencon.asset.dto.assettype.InsertAssetTypeDtoData;
import com.lawencon.asset.dto.assettype.InsertAssetTypeDtoReq;
import com.lawencon.asset.dto.assettype.InsertAssetTypeDtoRes;
import com.lawencon.asset.dto.assettype.UpdateAssetTypeDtoData;
import com.lawencon.asset.dto.assettype.UpdateAssetTypeDtoReq;
import com.lawencon.asset.dto.assettype.UpdateAssetTypeDtoRes;
import com.lawencon.asset.model.AssetType;
import com.lawencon.asset.service.AssetTypeService;

@Service
public class AssetTypeServiceImpl extends BaseServiceImpl implements AssetTypeService{

	private AssetTypeDao assetTypeDao;
	
	@Autowired
	public AssetTypeServiceImpl(AssetTypeDao assetTypeDao) {
		this.assetTypeDao = assetTypeDao;
	}

	@Override
	public GetAllAssetTypeDto getAll() {
		GetAllAssetTypeDto allAssetTypeDto = new GetAllAssetTypeDto();
		List<AssetTypeModelDto> datas = new ArrayList<AssetTypeModelDto>();
		List<AssetType> assetTypees = assetTypeDao.getAll();
		for (AssetType assetType : assetTypees) {
			AssetTypeModelDto data = new AssetTypeModelDto();
			data.setId(assetType.getId());
			data.setIsActive(assetType.getIsActive());
			data.setTypeCode(assetType.getTypeCode());
			data.setTypeName(assetType.getTypeName());
			data.setVersion(assetType.getVersion());
			datas.add(data);
		}
		allAssetTypeDto.setData(datas);
		return allAssetTypeDto;
	}

	@Override
	public GetByIdAssetTypeDto getById(Long id) {
		GetByIdAssetTypeDto assetTypeDto = new GetByIdAssetTypeDto();
		AssetTypeModelDto data = new AssetTypeModelDto();
		AssetType assetType = assetTypeDao.getById(id);
		data.setId(assetType.getId());
		data.setIsActive(assetType.getIsActive());
		data.setTypeCode(assetType.getTypeCode());
		data.setTypeName(assetType.getTypeName());
		data.setVersion(assetType.getVersion());
		assetTypeDto.setData(data);
		return assetTypeDto;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertAssetTypeDtoRes insert(InsertAssetTypeDtoReq assetType) {
		InsertAssetTypeDtoRes assetTypeDtoRes = new InsertAssetTypeDtoRes();
		InsertAssetTypeDtoData data = new InsertAssetTypeDtoData();
		AssetType assetTypeInsert = new AssetType();
		assetTypeInsert.setTypeCode(assetType.getTypeCode());
		assetTypeInsert.setTypeName(assetType.getTypeName());
		assetTypeInsert.setCreatedBy(getUserId());
		AssetType newAssetType = assetTypeDao.save(assetTypeInsert);
		if(newAssetType!=null) {
			data.setId(newAssetType.getId());
			assetTypeDtoRes.setData(data);
			assetTypeDtoRes.setMsg("berhasil insert asset type");
		}
		return assetTypeDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateAssetTypeDtoRes update(UpdateAssetTypeDtoReq assetType) {
		UpdateAssetTypeDtoRes assetTypeDtoRes = new UpdateAssetTypeDtoRes();
		UpdateAssetTypeDtoData data = new UpdateAssetTypeDtoData();
		
		AssetType assetTypeUpdate = assetTypeDao.getById(assetType.getId());
		assetTypeUpdate.setTypeName(assetType.getTypeName());
		assetTypeUpdate.setUpdatedBy(getUserId());
		AssetType newAssetType = assetTypeDao.save(assetTypeUpdate);
		if(newAssetType!=null) {
			data.setVersion(newAssetType.getVersion());
			assetTypeDtoRes.setData(data);
			assetTypeDtoRes.setMsg("berhasil update asset type");
		}
		return assetTypeDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteAssetTypeDtoRes delete(Long id) {
		DeleteAssetTypeDtoRes assetTypeDtoRes = new DeleteAssetTypeDtoRes();
		Boolean result = assetTypeDao.delete(id);
		if(result) assetTypeDtoRes.setMsg("Berhasil delete");
		else assetTypeDtoRes.setMsg("Gagal delete");
		return assetTypeDtoRes;
	}

}
