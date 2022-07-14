package com.lawencon.asset.dao.impl.hibernatehql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.AssetStatusDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.AssetStatus;

@Profile("hql")
@Repository
public class AssetStatusDaoHibernateHqlImpl extends BaseDaoImpl implements AssetStatusDao{

	@Override
	public List<AssetStatus> getAll() {
		String sql = "SELECT ass "
				+ "FROM AssetStatus ass "
				+ "ORDER BY ass.id";
		List<AssetStatus> assetStatuses = em.createQuery(sql, AssetStatus.class).getResultList();
		return assetStatuses;
	}

	@Override
	public AssetStatus save(AssetStatus assetStatus) {
		if(assetStatus.getId()!=null) {
			assetStatus = em.merge(assetStatus);
			em.flush();
		}else {
			em.persist(assetStatus);
		}
		em.detach(assetStatus);
		return assetStatus;
	}

	@Override
	public AssetStatus getById(Long id) {
		AssetStatus assetStatus = em.find(AssetStatus.class, id);
		em.detach(assetStatus);
		return assetStatus;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM AssetStatus WHERE id = :id";
		int result = em.createQuery(sql)
						.setParameter("id", id)
						.executeUpdate();
		return result>0;
	}

}
