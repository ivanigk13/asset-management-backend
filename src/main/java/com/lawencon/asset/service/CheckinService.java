package com.lawencon.asset.service;

import com.lawencon.asset.dto.checkin.GetAllCheckinDto;
import com.lawencon.asset.dto.checkin.GetByIdCheckinDto;
import com.lawencon.asset.dto.checkin.InsertCheckinDtoReq;
import com.lawencon.asset.dto.checkin.InsertCheckinDtoRes;

public interface CheckinService {
	GetAllCheckinDto getAll();
	GetByIdCheckinDto getById(Long id);
	InsertCheckinDtoRes insert(InsertCheckinDtoReq checkin);
}
