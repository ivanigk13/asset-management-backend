package com.lawencon.asset.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.asset.dao.CompanyDao;
import com.lawencon.asset.dto.company.CompanyModelDto;
import com.lawencon.asset.dto.company.DeleteCompanyDtoRes;
import com.lawencon.asset.dto.company.GetAllCompanyDto;
import com.lawencon.asset.dto.company.GetByIdCompanyDto;
import com.lawencon.asset.dto.company.InsertCompanyDtoData;
import com.lawencon.asset.dto.company.InsertCompanyDtoReq;
import com.lawencon.asset.dto.company.InsertCompanyDtoRes;
import com.lawencon.asset.dto.company.UpdateCompanyDtoData;
import com.lawencon.asset.dto.company.UpdateCompanyDtoReq;
import com.lawencon.asset.dto.company.UpdateCompanyDtoRes;
import com.lawencon.asset.model.Company;
import com.lawencon.asset.service.CompanyService;

@Service
public class CompanyServiceImpl extends BaseServiceImpl implements CompanyService{
	
	private CompanyDao companyDao;
	
	@Autowired
	public CompanyServiceImpl(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	public GetAllCompanyDto getAll() {
		GetAllCompanyDto allCompanyDto = new GetAllCompanyDto();
		List<CompanyModelDto> datas = new ArrayList<CompanyModelDto>();
		List<Company> companies = companyDao.getAll();
		for (Company company : companies) {
			CompanyModelDto data = new CompanyModelDto();
			data.setId(company.getId());
			data.setCompanyName(company.getCompanyName());
			data.setCompanyAddress(company.getCompanyAddress());
			data.setVersion(company.getVersion());
			data.setIsActive(company.getIsActive());
			datas.add(data);
		}
		allCompanyDto.setData(datas);
		return allCompanyDto;
	}

	@Override
	public GetByIdCompanyDto getById(Long id) {
		GetByIdCompanyDto getByIdCompanyDto = new GetByIdCompanyDto();
		CompanyModelDto data = new CompanyModelDto();
		Company company = companyDao.getById(id);
		data.setId(company.getId());
		data.setCompanyName(company.getCompanyName());
		data.setCompanyAddress(company.getCompanyAddress());
		data.setVersion(company.getVersion());
		data.setIsActive(company.getIsActive());
		getByIdCompanyDto.setData(data);
		return getByIdCompanyDto;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public InsertCompanyDtoRes insert(InsertCompanyDtoReq company) {
		InsertCompanyDtoRes companyDtoRes = new InsertCompanyDtoRes();
		InsertCompanyDtoData data = new InsertCompanyDtoData();
		Company companyInsert = new Company();
		companyInsert.setCompanyName(company.getCompanyName());
		companyInsert.setCompanyAddress(company.getCompanyAddress());
		companyInsert.setCreatedBy(getUserId());
		Company newCompany = companyDao.save(companyInsert);
		if(newCompany!=null) {
			data.setId(newCompany.getId());
			companyDtoRes.setData(data);
			companyDtoRes.setMsg("berhasil insert company");
		}
		return companyDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public UpdateCompanyDtoRes update(UpdateCompanyDtoReq company) {
		UpdateCompanyDtoRes companyDtoRes = new UpdateCompanyDtoRes();
		UpdateCompanyDtoData data = new UpdateCompanyDtoData();
		
		Company companyUpdate = companyDao.getById(company.getId());
		companyUpdate.setId(company.getId());
		companyUpdate.setCompanyName(company.getCompanyName());
		companyUpdate.setCompanyAddress(company.getCompanyAddress());
		companyUpdate.setVersion(company.getVersion());
		companyUpdate.setUpdatedBy(getUserId());
		Company newCompany = companyDao.save(companyUpdate);
		if(newCompany!=null) {
			data.setVersion(newCompany.getVersion());
			companyDtoRes.setData(data);
			companyDtoRes.setMsg("berhasil update company");
		}
		return companyDtoRes;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public DeleteCompanyDtoRes delete(Long id) {
		DeleteCompanyDtoRes deleteCompanyDtoRes = new DeleteCompanyDtoRes();
		Boolean result = companyDao.delete(id);
		if(result) deleteCompanyDtoRes.setMsg("Berhasil delete");
		else deleteCompanyDtoRes.setMsg("Gagal delete");
		return deleteCompanyDtoRes;
	}

}
