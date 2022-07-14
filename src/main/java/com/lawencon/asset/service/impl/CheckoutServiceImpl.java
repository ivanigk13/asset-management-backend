package com.lawencon.asset.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.asset.dao.AssetDao;
import com.lawencon.asset.dao.AssetStatusDao;
import com.lawencon.asset.dao.CheckoutDao;
import com.lawencon.asset.dto.checkout.CheckoutModelDto;
import com.lawencon.asset.dto.checkout.GetAllCheckoutDto;
import com.lawencon.asset.dto.checkout.GetByIdCheckoutDto;
import com.lawencon.asset.dto.checkout.GetCheckoutExpiredDto;
import com.lawencon.asset.dto.checkout.GetUncheckinDto;
import com.lawencon.asset.dto.checkout.InsertCheckoutDtoData;
import com.lawencon.asset.dto.checkout.InsertCheckoutDtoReq;
import com.lawencon.asset.dto.checkout.InsertCheckoutDtoRes;
import com.lawencon.asset.model.Asset;
import com.lawencon.asset.model.AssetStatus;
import com.lawencon.asset.model.Checkout;
import com.lawencon.asset.model.Employee;
import com.lawencon.asset.service.CheckoutService;

@Service
public class CheckoutServiceImpl extends BaseServiceImpl implements CheckoutService{

	private CheckoutDao checkoutDao;
	private AssetDao assetDao;
	private AssetStatusDao assetStatusDao;
	
	@Autowired
	public CheckoutServiceImpl(CheckoutDao checkoutDao, AssetDao assetDao, AssetStatusDao assetStatusDao) {
		this.checkoutDao = checkoutDao;
		this.assetDao = assetDao;
		this.assetStatusDao = assetStatusDao;
	}

	@Override
	public GetAllCheckoutDto getAll() {
		GetAllCheckoutDto allCheckoutDto = new GetAllCheckoutDto();
		List<CheckoutModelDto> datas = new ArrayList<CheckoutModelDto>();
		List<Checkout> checkouts = checkoutDao.getAll();
		for (Checkout checkout : checkouts) {
			CheckoutModelDto data = new CheckoutModelDto();
			data.setId(checkout.getId());
			data.setAssetName(checkout.getAsset().getAssetName());
			Employee employee = checkout.getEmployee();
			Asset otherAsset = checkout.getOtherAsset();
			String place = checkout.getPlace();
			LocalDate expired = checkout.getExpired();
			
			if(employee!=null) data.setEmployeeName(employee.getEmployeeName());
			if(otherAsset!=null) data.setOtherAssetName(otherAsset.getAssetName());
			if(place!=null) data.setPlace(place);
			if(expired!=null) data.setExpired(expired);
			data.setVersion(checkout.getVersion());
			data.setIsActive(checkout.getIsActive());
			datas.add(data);
		}
		allCheckoutDto.setData(datas);
		return allCheckoutDto;
	}

	@Override
	public GetByIdCheckoutDto getById(Long id) {
		GetByIdCheckoutDto checkoutDto = new GetByIdCheckoutDto();
		CheckoutModelDto data = new CheckoutModelDto();
		Checkout checkout = checkoutDao.getById(id);
		data.setId(checkout.getId());
		data.setAssetName(checkout.getAsset().getAssetName());
		String employeeName = (checkout.getEmployee().getId()!=null) ? checkout.getEmployee().getEmployeeName() : "-";
		String otherAssetName = (checkout.getOtherAsset().getId()!=null) ? checkout.getOtherAsset().getAssetName() : "-";
		String place = (checkout.getPlace()!=null) ? checkout.getPlace() : "-";
		data.setEmployeeName(employeeName);
		data.setOtherAssetName(otherAssetName);
		data.setPlace(place);
		if(checkout.getExpired()!=null) data.setExpired(checkout.getExpired());
		data.setVersion(checkout.getVersion());
		data.setIsActive(checkout.getIsActive());
		checkoutDto.setData(data);
		return checkoutDto;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertCheckoutDtoRes insert(InsertCheckoutDtoReq checkout) {
		InsertCheckoutDtoRes checkoutDtoRes = new InsertCheckoutDtoRes();
		InsertCheckoutDtoData data = new InsertCheckoutDtoData();
		
		Checkout checkoutInsert = new Checkout();
		Asset asset = new Asset();
		asset.setId(checkout.getAssetId());
		asset.setVersion(0);
		
		Employee employee = new Employee();
		Asset otherAsset = new Asset();
		String place = "";
		if(checkout.getEmployeeId()!=null) {
			employee.setId(checkout.getEmployeeId());
			employee.setVersion(0);
			otherAsset = null;
			place = null;
		}else if(checkout.getOtherAssetId()!=null) {
			otherAsset.setId(checkout.getOtherAssetId());
			otherAsset.setVersion(0);
			employee = null;
			place = null;			
		}else{
			place = checkout.getPlace();
			employee = null;
			otherAsset = null;
		}
		
		checkoutInsert.setAsset(asset);
		checkoutInsert.setEmployee(employee);
		checkoutInsert.setOtherAsset(otherAsset);
		checkoutInsert.setPlace(place);
		if(checkout.getExpired()!=null)checkoutInsert.setExpired(checkout.getExpired());
		checkoutInsert.setCreatedBy(getUserId());
		Checkout newCheckout = checkoutDao.save(checkoutInsert);
		if(newCheckout!=null) {
			data.setId(newCheckout.getId());
			checkoutDtoRes.setData(data);
			checkoutDtoRes.setMsg("berhasil insert checkout");
		}
		
		Asset assetToBeUpdated = assetDao.getById(checkout.getAssetId());
		AssetStatus assetStatus = assetStatusDao.getById(2L);
		assetToBeUpdated.setAssetStatus(assetStatus);
		assetDao.save(assetToBeUpdated);
		return checkoutDtoRes;
	}
	
	@Override
	public GetUncheckinDto getUncheckin() {
		GetUncheckinDto getUncheckinDto = new GetUncheckinDto();
		List<CheckoutModelDto> datas = new ArrayList<CheckoutModelDto>();
		List<Checkout> checkouts = checkoutDao.getUncheckin();
		for (Checkout checkout : checkouts) {
			CheckoutModelDto data = new CheckoutModelDto();
			Employee employee = checkout.getEmployee();
			Asset otherAsset = checkout.getOtherAsset();
			String place = checkout.getPlace();
			LocalDate expired = checkout.getExpired();
			
			data.setId(checkout.getId());
			data.setAssetName(checkout.getAsset().getAssetName());
			if(employee!=null) {
				data.setEmployeeName(employee.getEmployeeName());
			}
			if(otherAsset!=null) {
				data.setOtherAssetName(otherAsset.getAssetName());
			}
			if(place!=null) data.setPlace(place);
			if(expired!=null)data.setExpired(checkout.getExpired());
			data.setVersion(checkout.getVersion());
			datas.add(data);
		}
		getUncheckinDto.setData(datas);
		return getUncheckinDto;
	}

	@Override
	public GetCheckoutExpiredDto getCheckoutExpired() {
		GetCheckoutExpiredDto checkoutExpiredDto = new GetCheckoutExpiredDto();
		List<CheckoutModelDto> datas = new ArrayList<CheckoutModelDto>();
		List<Checkout> checkouts = checkoutDao.getCheckoutExpired();
		for (Checkout checkout : checkouts) {
			CheckoutModelDto data = new CheckoutModelDto();
			Employee employee = checkout.getEmployee();
			Asset otherAsset = checkout.getOtherAsset();
			String place = checkout.getPlace();
			LocalDate expired = checkout.getExpired();
			
			data.setId(checkout.getId());
			data.setAssetName(checkout.getAsset().getAssetName());
			if(employee!=null)data.setEmployeeName(employee.getEmployeeName());
			if(otherAsset!=null)data.setOtherAssetName(otherAsset.getAssetName());
			if(place!=null)data.setPlace(place);
			if(expired!=null)data.setExpired(checkout.getExpired());
			data.setVersion(checkout.getVersion());
			datas.add(data);
		}
		checkoutExpiredDto.setData(datas);
		return checkoutExpiredDto;
	}

}
