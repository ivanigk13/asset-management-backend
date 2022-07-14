package com.lawencon.asset.dao;

import com.lawencon.asset.model.File;

public interface FileDao {
	File getById(Long id);
	File save(File file);
	boolean delete(Long id);
}
