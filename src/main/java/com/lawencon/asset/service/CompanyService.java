package com.lawencon.asset.service;

import com.lawencon.asset.dto.company.DeleteCompanyDtoRes;
import com.lawencon.asset.dto.company.GetAllCompanyDto;
import com.lawencon.asset.dto.company.GetByIdCompanyDto;
import com.lawencon.asset.dto.company.InsertCompanyDtoReq;
import com.lawencon.asset.dto.company.InsertCompanyDtoRes;
import com.lawencon.asset.dto.company.UpdateCompanyDtoReq;
import com.lawencon.asset.dto.company.UpdateCompanyDtoRes;

public interface CompanyService {
	GetAllCompanyDto getAll();
	GetByIdCompanyDto getById(Long id);
	InsertCompanyDtoRes insert(InsertCompanyDtoReq company);
	UpdateCompanyDtoRes update(UpdateCompanyDtoReq company);
	DeleteCompanyDtoRes delete(Long id);
}
