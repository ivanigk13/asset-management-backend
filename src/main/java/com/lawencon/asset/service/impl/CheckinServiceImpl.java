package com.lawencon.asset.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.asset.dao.AssetDao;
import com.lawencon.asset.dao.CheckinDao;
import com.lawencon.asset.dao.CheckoutDao;
import com.lawencon.asset.dto.checkin.CheckinModelDto;
import com.lawencon.asset.dto.checkin.GetAllCheckinDto;
import com.lawencon.asset.dto.checkin.GetByIdCheckinDto;
import com.lawencon.asset.dto.checkin.InsertCheckinDtoData;
import com.lawencon.asset.dto.checkin.InsertCheckinDtoReq;
import com.lawencon.asset.dto.checkin.InsertCheckinDtoRes;
import com.lawencon.asset.model.Asset;
import com.lawencon.asset.model.AssetStatus;
import com.lawencon.asset.model.Checkin;
import com.lawencon.asset.model.Checkout;
import com.lawencon.asset.service.CheckinService;

@Service
public class CheckinServiceImpl extends BaseServiceImpl implements CheckinService{

	private CheckinDao checkinDao;
	private CheckoutDao checkoutDao;
	private AssetDao assetDao;
	
	@Autowired
	public CheckinServiceImpl(CheckinDao checkinDao, CheckoutDao checkoutDao, AssetDao assetDao) {
		this.checkinDao = checkinDao;
		this.checkoutDao = checkoutDao;
		this.assetDao = assetDao;
	}

	@Override
	public GetAllCheckinDto getAll() {
		GetAllCheckinDto allCheckinDto = new GetAllCheckinDto();
		List<CheckinModelDto> datas = new ArrayList<CheckinModelDto>();
		List<Checkin> checkins = checkinDao.getAll();
		for (Checkin checkin : checkins) {
			CheckinModelDto data = new CheckinModelDto();
			data.setId(checkin.getId());
			data.setAssetName(checkin.getCheckout().getAsset().getAssetName());
			data.setStatusName(checkin.getAssetStatus().getStatusName());
			data.setAltName(checkin.getAssetStatus().getAltName());
			data.setVersion(checkin.getVersion());
			datas.add(data);
		}
		allCheckinDto.setData(datas);
		return allCheckinDto;
	}

	@Override
	public GetByIdCheckinDto getById(Long id) {
		GetByIdCheckinDto checkinDto = new GetByIdCheckinDto();
		CheckinModelDto data = new CheckinModelDto();
		Checkin checkin = checkinDao.getById(id);
		data.setId(checkin.getId());
		data.setAssetName(checkin.getCheckout().getAsset().getAssetName());
		data.setStatusName(checkin.getAssetStatus().getStatusName());
		data.setAltName(checkin.getAssetStatus().getAltName());
		data.setVersion(checkin.getVersion());
		checkinDto.setData(data);
		return checkinDto;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertCheckinDtoRes insert(InsertCheckinDtoReq checkin) {
		InsertCheckinDtoRes checkinDtoRes = new InsertCheckinDtoRes();
		InsertCheckinDtoData data = new InsertCheckinDtoData();
		Checkin checkinInsert = new Checkin();
		
		Checkout checkout = new Checkout();
		checkout.setId(checkin.getCheckoutId());
		checkout.setVersion(0);
		
		AssetStatus assetStatus = new AssetStatus();
		assetStatus.setId(checkin.getAssetStatusId());
		assetStatus.setVersion(0);
		
		checkinInsert.setAssetStatus(assetStatus);
		checkinInsert.setCheckout(checkout);
		checkinInsert.setCreatedBy(getUserId());
		
		Checkin newCheckin = checkinDao.save(checkinInsert);
		if(newCheckin!=null) {
			data.setId(newCheckin.getId());
			checkinDtoRes.setData(data);
			checkinDtoRes.setMsg("berhasil insert checkin");
		}
		
		Checkout getCheckout = checkoutDao.getById(checkin.getCheckoutId());
		Asset asset = assetDao.getById(getCheckout.getAsset().getId());
		asset.setAssetStatus(newCheckin.getAssetStatus());
		assetDao.save(asset);
		return checkinDtoRes;
	}

}
