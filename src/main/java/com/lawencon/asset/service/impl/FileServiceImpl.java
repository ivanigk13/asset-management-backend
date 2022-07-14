package com.lawencon.asset.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.asset.dao.FileDao;
import com.lawencon.asset.dto.file.FileModelDto;
import com.lawencon.asset.dto.file.GetByIdFileDto;
import com.lawencon.asset.model.File;
import com.lawencon.asset.service.FileService;

@Service
public class FileServiceImpl extends BaseServiceImpl implements FileService{

	private FileDao fileDao;
	
	@Autowired
	public FileServiceImpl(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public GetByIdFileDto getById(Long id) {
		GetByIdFileDto getByIdFileDto = new GetByIdFileDto();
		File file = fileDao.getById(id);
		FileModelDto data = new FileModelDto();
		data.setId(file.getId());
		data.setContent(file.getContent());
		data.setExtensionName(file.getExtensionName());
		data.setVersion(file.getVersion());
		data.setIsActive(file.getIsActive());
		getByIdFileDto.setData(data);
		return getByIdFileDto;
	}

	@Override
	public boolean delete(Long id) {
		return fileDao.delete(id);
	}

}
