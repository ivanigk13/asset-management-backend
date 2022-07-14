package com.lawencon.asset.dao.impl.hibernatehql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.AssetTypeDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.AssetType;

@Profile("hql")
@Repository
public class AssetTypeDaoHibernateHqlImpl extends BaseDaoImpl implements AssetTypeDao{

	@Override
	public List<AssetType> getAll() {
		String sql = "SELECT ast "
				+ "FROM AssetType ast "
				+ "ORDER BY ast.id";
		List<AssetType> assetTypes = em.createQuery(sql, AssetType.class).getResultList();
		return assetTypes;
	}

	@Override
	public AssetType save(AssetType assetType) {
		if(assetType.getId()!=null) {
			assetType = em.merge(assetType);
			em.flush();
		}else {
			em.persist(assetType);
		}		
		em.detach(assetType);
		return assetType;
	}

	@Override
	public AssetType getById(Long id) {
		AssetType assetType = em.find(AssetType.class, id);
		em.detach(assetType);
		return assetType;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM AssetType WHERE id = :id";
		int result = em.createQuery(sql)
						.setParameter("id", id)
						.executeUpdate();
		return result>0;
	}

}
