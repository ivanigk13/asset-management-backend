package com.lawencon.asset.service;

import com.lawencon.asset.dto.file.GetByIdFileDto;

public interface FileService {
	GetByIdFileDto getById(Long id);
	boolean delete(Long id);
}
