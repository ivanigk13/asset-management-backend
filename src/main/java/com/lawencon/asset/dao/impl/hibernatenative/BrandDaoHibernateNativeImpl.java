package com.lawencon.asset.dao.impl.hibernatenative;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.BrandDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Brand;

@Profile("native")
@Repository
public class BrandDaoHibernateNativeImpl extends BaseDaoImpl implements BrandDao{

	@Override
	public List<Brand> getAll() {
		String sql = "SELECT id, brand_name, created_by, created_at, updated_by, updated_at, version, is_active "
				+ "FROM brand";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<Brand> brands = new ArrayList<Brand>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			Brand brand = new Brand();
			brand.setId(Long.valueOf(obj[0].toString()));
			brand.setBrandName(obj[1].toString());
			
			if(obj[2]!=null)brand.setCreatedBy(Long.valueOf(obj[2].toString()));
			if(obj[3]!=null)brand.setCreatedAt(Timestamp.valueOf(obj[3].toString()).toLocalDateTime());			
			
			if(obj[4]!=null) brand.setUpdatedBy(Long.valueOf(obj[4].toString()));
			if(obj[5]!=null) brand.setUpdatedAt(Timestamp.valueOf(obj[4].toString()).toLocalDateTime());
			
			brand.setVersion(Integer.valueOf(obj[6].toString()));
			brand.setIsActive(Boolean.valueOf(obj[7].toString()));
			brands.add(brand);
		});
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
		String sql = "DELETE FROM brand WHERE id = :id";
		int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		return result>0;
	}

}
