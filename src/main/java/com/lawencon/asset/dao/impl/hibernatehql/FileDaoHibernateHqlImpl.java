package com.lawencon.asset.dao.impl.hibernatehql;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.FileDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.File;

@Profile("hql")
@Repository
public class FileDaoHibernateHqlImpl extends BaseDaoImpl implements FileDao{

	@Override
	public File getById(Long id) {
		File file = em.find(File.class, id);
		em.detach(file);
		return file;
	}

	@Override
	public File save(File file) {
		if(file.getId()!=null) {
			file = em.merge(file);
			em.flush();
		}else {
			em.persist(file);
		}
		em.detach(file);
		return file;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM File WHERE id = :id";
		int result = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return result>0;
	}

}
