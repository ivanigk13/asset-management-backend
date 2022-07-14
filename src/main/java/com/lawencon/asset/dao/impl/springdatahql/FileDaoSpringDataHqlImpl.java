package com.lawencon.asset.dao.impl.springdatahql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.FileDao;
import com.lawencon.asset.model.File;
import com.lawencon.asset.repository.springdatahql.FileHqlRepository;

@Profile("jpahql")
@Repository
public class FileDaoSpringDataHqlImpl implements FileDao{

	private FileHqlRepository fileHqlRepository;
	
	@Autowired
	public void setFileHqlRepository(FileHqlRepository fileHqlRepository) {
		this.fileHqlRepository = fileHqlRepository;
	}

	@Override
	public File save(File file) {
		return fileHqlRepository.saveAndFlush(file);
	}

	@Override
	public File getById(Long id) {
		return fileHqlRepository.getById(id);
	}

	@Override
	public boolean delete(Long id) {
		int result = fileHqlRepository.removeById(id);
		return result>0;
	}

}
