package com.lawencon.asset.service;

import com.lawencon.asset.dto.checkout.GetAllCheckoutDto;
import com.lawencon.asset.dto.checkout.GetByIdCheckoutDto;
import com.lawencon.asset.dto.checkout.GetCheckoutExpiredDto;
import com.lawencon.asset.dto.checkout.GetUncheckinDto;
import com.lawencon.asset.dto.checkout.InsertCheckoutDtoReq;
import com.lawencon.asset.dto.checkout.InsertCheckoutDtoRes;

public interface CheckoutService {
	GetAllCheckoutDto getAll();
	GetByIdCheckoutDto getById(Long id);
	InsertCheckoutDtoRes insert(InsertCheckoutDtoReq checkout);
	GetUncheckinDto getUncheckin();
	GetCheckoutExpiredDto getCheckoutExpired();
}
