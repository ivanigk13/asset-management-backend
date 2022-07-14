package com.lawencon.asset.dao.impl.hibernatehql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.constant.StatusList;
import com.lawencon.asset.dao.AssetDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Asset;

@Profile("hql")
@Repository
public class AssetDaoHibernateHqlImpl extends BaseDaoImpl implements AssetDao{

	@Override
	public List<Asset> getAll() {
		String sql = "SELECT a FROM Asset as a "
				+ "LEFT JOIN FETCH a.company as c "
				+ "LEFT JOIN FETCH a.assetType as ast "
				+ "LEFT JOIN FETCH a.assetStatus as ass "
				+ "LEFT JOIN FETCH a.brand as b "
				+ "LEFT JOIN FETCH a.file as f "
				+ "ORDER BY a.id ASC";
		List<Asset> assets = em.createQuery(sql, Asset.class).getResultList();
		return assets;
	}

	@Override
	public Asset save(Asset asset) {
		if(asset.getId()!=null) {
			asset = em.merge(asset);
			em.flush();
		}else {
			em.persist(asset);
		}
		em.detach(asset);
		return asset;
	}

	@Override
	public Asset getById(Long id) {
		Asset asset = em.find(Asset.class, id);
		em.detach(asset);
		return asset;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM Asset WHERE id = :id";
		int result = em.createQuery(sql)
						.setParameter("id", id)
						.executeUpdate();
		return result>0;
	}

	@Override
	public List<Asset> getReadyToUse() {
		String sql = "SELECT a FROM Asset a "
				+ "LEFT JOIN FETCH a.company as c "
				+ "LEFT JOIN FETCH a.assetType as ast "
				+ "LEFT JOIN FETCH a.assetStatus as ass "
				+ "LEFT JOIN FETCH a.brand as b "
				+ "LEFT JOIN FETCH a.file as f "
				+ "WHERE a.assetStatus.id = (SELECT id FROM AssetStatus WHERE statusCode = :statusCode) "
				+ "ORDER BY a.id ASC";
		List<Asset> assets = em.createQuery(sql, Asset.class)
								.setParameter("statusCode", StatusList.READY_TO_DEPLOY.code)
								.getResultList();
		return assets;
	}

	@Override
	public List<Asset> getExpiredAsset() {
		String sql = "SELECT a FROM Asset a "
				+ "LEFT JOIN FETCH a.company as c "
				+ "LEFT JOIN FETCH a.assetType as ast "
				+ "LEFT JOIN FETCH a.assetStatus as ass "
				+ "LEFT JOIN FETCH a.brand as b "
				+ "LEFT JOIN FETCH a.file as f "
				+ "WHERE expired <= CURRENT_TIMESTAMP "
				+ "ORDER BY a.id ASC";
		List<Asset> assets = em.createQuery(sql, Asset.class).getResultList();
		return assets;
	}

}
