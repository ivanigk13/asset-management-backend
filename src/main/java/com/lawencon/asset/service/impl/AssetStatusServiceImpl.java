package com.lawencon.asset.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.asset.dao.AssetStatusDao;
import com.lawencon.asset.dto.assetstatus.AssetStatusModelDto;
import com.lawencon.asset.dto.assetstatus.DeleteAssetStatusDtoRes;
import com.lawencon.asset.dto.assetstatus.GetAllAssetStatusDto;
import com.lawencon.asset.dto.assetstatus.GetByIdAssetStatusDto;
import com.lawencon.asset.dto.assetstatus.InsertAssetStatusDtoData;
import com.lawencon.asset.dto.assetstatus.InsertAssetStatusDtoReq;
import com.lawencon.asset.dto.assetstatus.InsertAssetStatusDtoRes;
import com.lawencon.asset.dto.assetstatus.UpdateAssetStatusDtoData;
import com.lawencon.asset.dto.assetstatus.UpdateAssetStatusDtoReq;
import com.lawencon.asset.dto.assetstatus.UpdateAssetStatusDtoRes;
import com.lawencon.asset.model.AssetStatus;
import com.lawencon.asset.service.AssetStatusService;

@Service
public class AssetStatusServiceImpl extends BaseServiceImpl implements AssetStatusService{

	private AssetStatusDao assetStatusDao;
	
	@Autowired
	public AssetStatusServiceImpl(AssetStatusDao assetStatusDao) {
		this.assetStatusDao = assetStatusDao;
	}

	@Override
	public GetAllAssetStatusDto getAll() {
		GetAllAssetStatusDto allAssetStatusDto = new GetAllAssetStatusDto();
		List<AssetStatusModelDto> datas = new ArrayList<AssetStatusModelDto>();
		List<AssetStatus> assetStatuses = assetStatusDao.getAll();
		for (AssetStatus assetStatus : assetStatuses) {
			AssetStatusModelDto data = new AssetStatusModelDto();
			data.setId(assetStatus.getId());
			data.setIsActive(assetStatus.getIsActive());
			data.setStatusCode(assetStatus.getStatusCode());
			data.setStatusName(assetStatus.getStatusName());
			data.setAltName(assetStatus.getAltName());
			data.setVersion(assetStatus.getVersion());
			datas.add(data);
		}
		allAssetStatusDto.setData(datas);
		return allAssetStatusDto;
	}

	@Override
	public GetByIdAssetStatusDto getById(Long id) {
		GetByIdAssetStatusDto assetStatusDto = new GetByIdAssetStatusDto();
		AssetStatusModelDto data = new AssetStatusModelDto();
		AssetStatus assetStatus = assetStatusDao.getById(id);
		data.setId(assetStatus.getId());
		data.setIsActive(assetStatus.getIsActive());
		data.setStatusCode(assetStatus.getStatusCode());
		data.setStatusName(assetStatus.getStatusName());
		data.setAltName(assetStatus.getAltName());
		data.setVersion(assetStatus.getVersion());
		assetStatusDto.setData(data);
		return assetStatusDto;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertAssetStatusDtoRes insert(InsertAssetStatusDtoReq assetStatus) {
		InsertAssetStatusDtoRes assetStatusDtoRes = new InsertAssetStatusDtoRes();
		InsertAssetStatusDtoData data = new InsertAssetStatusDtoData();
		AssetStatus assetStatusInsert = new AssetStatus();
		assetStatusInsert.setStatusCode(assetStatus.getStatusCode());
		assetStatusInsert.setAltName(assetStatus.getAltName());
		assetStatusInsert.setStatusName(assetStatus.getStatusName());
		assetStatusInsert.setCreatedBy(getUserId());
		AssetStatus newAssetStatus = assetStatusDao.save(assetStatusInsert);
		if(newAssetStatus!=null) {
			data.setId(newAssetStatus.getId());
			assetStatusDtoRes.setData(data);
			assetStatusDtoRes.setMsg("berhasil insert asset status");
		}
		return assetStatusDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateAssetStatusDtoRes update(UpdateAssetStatusDtoReq assetStatus) {
		UpdateAssetStatusDtoRes assetStatusDtoRes = new UpdateAssetStatusDtoRes();
		UpdateAssetStatusDtoData data = new UpdateAssetStatusDtoData();
		
		AssetStatus assetStatusUpdate = assetStatusDao.getById(assetStatus.getId());
		assetStatusUpdate.setStatusName(assetStatus.getStatusName());
		assetStatusUpdate.setAltName(assetStatus.getAltName());
		assetStatusUpdate.setVersion(assetStatus.getVersion());
		assetStatusUpdate.setUpdatedBy(getUserId());
		
		AssetStatus newAssetStatus = assetStatusDao.save(assetStatusUpdate);
		if(newAssetStatus!=null) {
			data.setVersion(newAssetStatus.getVersion());
			assetStatusDtoRes.setData(data);
			assetStatusDtoRes.setMsg("berhasil update asset status");
		}
		return assetStatusDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteAssetStatusDtoRes delete(Long id) {
		DeleteAssetStatusDtoRes assetStatusDtoRes = new DeleteAssetStatusDtoRes();
		Boolean result = assetStatusDao.delete(id);
		if(result) assetStatusDtoRes.setMsg("Berhasil delete");
		else assetStatusDtoRes.setMsg("Gagal delete");
		return assetStatusDtoRes;
	}

}
