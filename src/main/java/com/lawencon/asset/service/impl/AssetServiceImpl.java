package com.lawencon.asset.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.asset.dao.AssetDao;
import com.lawencon.asset.dao.FileDao;
import com.lawencon.asset.dto.asset.AssetModelDto;
import com.lawencon.asset.dto.asset.DeleteAssetDtoRes;
import com.lawencon.asset.dto.asset.GetAllAssetDto;
import com.lawencon.asset.dto.asset.GetByIdAssetDto;
import com.lawencon.asset.dto.asset.GetExpiredAssetDto;
import com.lawencon.asset.dto.asset.GetReadyToUseDto;
import com.lawencon.asset.dto.asset.InsertAssetDtoData;
import com.lawencon.asset.dto.asset.InsertAssetDtoReq;
import com.lawencon.asset.dto.asset.InsertAssetDtoRes;
import com.lawencon.asset.dto.asset.UpdateAssetDtoData;
import com.lawencon.asset.dto.asset.UpdateAssetDtoReq;
import com.lawencon.asset.dto.asset.UpdateAssetDtoRes;
import com.lawencon.asset.model.Asset;
import com.lawencon.asset.model.AssetStatus;
import com.lawencon.asset.model.AssetType;
import com.lawencon.asset.model.Brand;
import com.lawencon.asset.model.Company;
import com.lawencon.asset.model.File;
import com.lawencon.asset.service.AssetService;

@Service
public class AssetServiceImpl extends BaseServiceImpl implements AssetService{

	private AssetDao assetDao;
	private FileDao fileDao;
	
	@Autowired
	public AssetServiceImpl(AssetDao assetDao, FileDao fileDao) {
		this.assetDao = assetDao;
		this.fileDao = fileDao;
	}
	
	@Override
	public GetAllAssetDto getAll() throws IOException {
		GetAllAssetDto allAssetDto = new GetAllAssetDto();
		List<AssetModelDto> datas = new ArrayList<AssetModelDto>();
		List<Asset> assets = assetDao.getAll();
		for (Asset asset : assets) {
			AssetModelDto data = new AssetModelDto();
			data.setBrandId(asset.getBrand().getId());
			data.setCompanyId(asset.getCompany().getId());
			data.setTypeId(asset.getAssetType().getId());
			data.setStatusId(asset.getAssetStatus().getId());
			data.setAssetDescription(asset.getAssetDescription());
			data.setAssetName(asset.getAssetName());
			data.setBrandName(asset.getBrand().getBrandName());
			data.setCompanyName(asset.getCompany().getCompanyName());
			if(asset.getExpired()!=null) data.setExpired(asset.getExpired());
			if(asset.getFile()!=null) {
				data.setFileContent(asset.getFile().getContent());
				data.setFileId(asset.getFile().getId());
			}
			data.setId(asset.getId());
			data.setInvoice(asset.getInvoice());
			data.setSerial(asset.getSerial());
			data.setStatusName(asset.getAssetStatus().getStatusName());
			data.setTypeName(asset.getAssetType().getTypeName());
			data.setVersion(asset.getVersion());
			data.setIsActive(asset.getIsActive());
			datas.add(data);
		}
		allAssetDto.setData(datas);
		return allAssetDto;
	}

	@Override
	public GetByIdAssetDto getById(Long id) {
		GetByIdAssetDto assetDto = new GetByIdAssetDto();
		AssetModelDto data = new AssetModelDto();
		Asset asset = assetDao.getById(id);
		data.setAssetDescription(asset.getAssetDescription());
		data.setAssetName(asset.getAssetName());
		data.setBrandId(asset.getBrand().getId());
		data.setBrandName(asset.getBrand().getBrandName());
		data.setCompanyId(asset.getCompany().getId());
		data.setCompanyName(asset.getCompany().getCompanyName());
		if(asset.getExpired()!=null) data.setExpired(asset.getExpired());
		if(asset.getFile()!=null) {
			data.setFileId(asset.getFile().getId());
			data.setFileContent(asset.getFile().getContent());
		}
		data.setId(asset.getId());
		data.setInvoice(asset.getInvoice());
		data.setSerial(asset.getSerial());
		data.setStatusId(asset.getAssetStatus().getId());
		data.setStatusName(asset.getAssetStatus().getStatusName());
		data.setTypeId(asset.getAssetType().getId());
		data.setTypeName(asset.getAssetType().getTypeName());
		data.setVersion(asset.getVersion());
		data.setIsActive(asset.getIsActive());
		assetDto.setData(data);
		return assetDto;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertAssetDtoRes insert(String reqData, MultipartFile file) throws IOException {
		InsertAssetDtoReq asset = new ObjectMapper().readValue(reqData, InsertAssetDtoReq.class);
		InsertAssetDtoRes assetDtoRes = new InsertAssetDtoRes();
		InsertAssetDtoData data = new InsertAssetDtoData();
		
		Asset assetInsert = new Asset();
		Company company = new Company();
		company.setId(asset.getCompanyId());
		company.setVersion(0);
		
		Brand brand = new Brand();
		brand.setId(asset.getBrandId());
		brand.setVersion(0);
		
		AssetStatus assetStatus = new AssetStatus();
		assetStatus.setId(asset.getAssetStatusId());
		assetStatus.setVersion(0);
		
		AssetType assetType = new AssetType();
		assetType.setId(asset.getAssetTypeId());
		assetType.setVersion(0);
		
		if(file!=null) {
			File getFile = new File();
			byte[] fileContent = file.getBytes();
			String extension = file.getOriginalFilename()
						.substring(
								file.getOriginalFilename().lastIndexOf(".")+1, 
								file.getOriginalFilename().length());
			getFile.setExtensionName(extension);
			getFile.setContent(fileContent);
			File newFile = fileDao.save(getFile);			
			assetInsert.setFile(newFile);
		}
		
		assetInsert.setAssetDescription(asset.getAssetDescription());
		assetInsert.setAssetName(asset.getAssetName());
		assetInsert.setBrand(brand);
		assetInsert.setCompany(company);
		
		Date expired = asset.getExpired();
		if(expired!=null) {
			assetInsert.setExpired(convertDateToLocalDate(expired));			
		}
		
		assetInsert.setInvoice(asset.getInvoice());
		assetInsert.setSerial(asset.getSerial());
		assetInsert.setAssetStatus(assetStatus);
		assetInsert.setAssetType(assetType);
		assetInsert.setCreatedBy(getUserId());
		
		Asset newAsset = assetDao.save(assetInsert);
		if(newAsset != null) {
			data.setId(newAsset.getId());
			assetDtoRes.setData(data);
			assetDtoRes.setMsg("berhasil insert asset");
		}
		return assetDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateAssetDtoRes update(String reqData, MultipartFile file) throws IOException {
		UpdateAssetDtoReq asset = new ObjectMapper().readValue(reqData, UpdateAssetDtoReq.class);
		UpdateAssetDtoRes assetDtoRes = new UpdateAssetDtoRes();
		UpdateAssetDtoData data = new UpdateAssetDtoData();
		
		Asset assetUpdate = assetDao.getById(asset.getId());
		Company company = new Company();
		company.setId(asset.getCompanyId());
		company.setVersion(0);
		
		Brand brand = new Brand();
		brand.setId(asset.getBrandId());
		brand.setVersion(0);
		
		AssetStatus assetStatus = new AssetStatus();
		assetStatus.setId(asset.getAssetStatusId());
		assetStatus.setVersion(0);
		
		AssetType assetType = new AssetType();
		assetType.setId(asset.getAssetTypeId());
		assetType.setVersion(0);

		if(file!=null) {
			File getFile = new File();
			byte[] fileContent = file.getBytes();
			String extension = file.getOriginalFilename()
						.substring(
								file.getOriginalFilename().lastIndexOf(".")+1, 
								file.getOriginalFilename().length());
			getFile.setExtensionName(extension);
			getFile.setContent(fileContent);
			File newFile = fileDao.save(getFile);			
			assetUpdate.setFile(newFile);
		}
		
		assetUpdate.setId(asset.getId());
		assetUpdate.setVersion(asset.getVersion());
		assetUpdate.setAssetDescription(asset.getAssetDescription());
		assetUpdate.setAssetName(asset.getAssetName());
		assetUpdate.setBrand(brand);
		assetUpdate.setCompany(company);
		
		Date expired = asset.getExpired();
		if(expired!=null) {
			assetUpdate.setExpired(convertDateToLocalDate(expired));			
		}
		
		assetUpdate.setInvoice(asset.getInvoice());
		assetUpdate.setSerial(asset.getSerial());
		assetUpdate.setAssetStatus(assetStatus);
		assetUpdate.setAssetType(assetType);
		assetUpdate.setUpdatedBy(getUserId());
		
		Asset newAsset = assetDao.save(assetUpdate);
		if(newAsset != null) {
			data.setVersion(newAsset.getVersion());
			assetDtoRes.setData(data);
			assetDtoRes.setMsg("berhasil update asset");
		}
		return assetDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteAssetDtoRes delete(Long id) {
		DeleteAssetDtoRes assetDtoRes = new DeleteAssetDtoRes();
		Boolean result = assetDao.delete(id);
		if(result) assetDtoRes.setMsg("Berhasil delete");
		else assetDtoRes.setMsg("Gagal delete");
		return assetDtoRes;
	}
	
	@Override
	public GetReadyToUseDto getReadyToUse() throws IOException {
		GetReadyToUseDto getReadyToUseDto = new GetReadyToUseDto();
		List<AssetModelDto> datas = new ArrayList<AssetModelDto>();
		List<Asset> assets = assetDao.getReadyToUse();
		for (Asset asset : assets) {
			AssetModelDto data = new AssetModelDto();
			data.setAssetDescription(asset.getAssetDescription());
			data.setAssetName(asset.getAssetName());
			data.setBrandName(asset.getBrand().getBrandName());
			data.setCompanyName(asset.getCompany().getCompanyName());
			data.setExpired(asset.getExpired());
			if(asset.getFile()!=null)data.setFileContent(asset.getFile().getContent());
			data.setId(asset.getId());
			data.setInvoice(asset.getInvoice());
			data.setSerial(asset.getSerial());
			data.setStatusName(asset.getAssetStatus().getStatusName());
			data.setTypeName(asset.getAssetType().getTypeName());
			data.setVersion(asset.getVersion());
			datas.add(data);
		}
		getReadyToUseDto.setData(datas);
		return getReadyToUseDto;
	}

	@Override
	public GetExpiredAssetDto getExpiredAsset() throws IOException {
		GetExpiredAssetDto assetDto = new GetExpiredAssetDto();
		List<AssetModelDto> datas = new ArrayList<AssetModelDto>();
		List<Asset> assets = assetDao.getExpiredAsset();
			for (Asset asset : assets) {
			AssetModelDto data = new AssetModelDto();
			data.setAssetDescription(asset.getAssetDescription());
			data.setAssetName(asset.getAssetName());
			data.setBrandName(asset.getBrand().getBrandName());
			data.setCompanyName(asset.getCompany().getCompanyName());
			data.setExpired(asset.getExpired());
			if(asset.getFile()!=null) {
				data.setFileId(asset.getFile().getId());
				data.setFileContent(asset.getFile().getContent());
			}
			data.setId(asset.getId());
			data.setInvoice(asset.getInvoice());
			data.setSerial(asset.getSerial());
			data.setStatusName(asset.getAssetStatus().getStatusName());
			data.setTypeName(asset.getAssetType().getTypeName());
			data.setVersion(asset.getVersion());
			datas.add(data);
		}
		assetDto.setData(datas);
		return assetDto;
	}
	
	private LocalDate convertDateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
	
}
