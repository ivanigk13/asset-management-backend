package com.lawencon.asset.dao.impl.hibernatehql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.BrandDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Brand;

@Profile("hql")
@Repository
public class BrandDaoHibernateHqlImpl extends BaseDaoImpl implements BrandDao{

	@Override
	public List<Brand> getAll() {
		String sql = "SELECT b FROM Brand b "
				+ "ORDER BY b.id";
		List<Brand> brands = em.createQuery(sql, Brand.class).getResultList();
		return brands;
	}

	@Override
	public Brand save(Brand brand) {
		if(brand.getId()!=null) {
			brand = em.merge(brand);
			em.flush();
		}else {
			em.persist(brand);
		}
		em.detach(brand);
		return brand;
	}

	@Override
	public Brand getById(Long id) {
		Brand brand = em.find(Brand.class, id);
		em.detach(brand);
		return brand;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM Brand WHERE id = :id";
		int result = em.createQuery(sql).setParameter("id", id).executeUpdate();
		return result>0;
	}

}
