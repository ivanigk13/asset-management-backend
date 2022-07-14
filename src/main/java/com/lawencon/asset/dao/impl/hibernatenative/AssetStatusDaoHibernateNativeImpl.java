package com.lawencon.asset.dao.impl.hibernatenative;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.AssetStatusDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.AssetStatus;

@Profile("native")
@Repository
public class AssetStatusDaoHibernateNativeImpl extends BaseDaoImpl implements AssetStatusDao{

	@Override
	public List<AssetStatus> getAll() {
		String sql = "SELECT id, status_name, alt_name, created_by, created_at, updated_by, updated_at, version, is_active "
				+ "FROM asset_status";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<AssetStatus> assetStatuses = new ArrayList<AssetStatus>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			AssetStatus assetStatus = new AssetStatus();
			assetStatus.setId(Long.valueOf(obj[0].toString()));
			assetStatus.setStatusName(obj[1].toString());
			assetStatus.setAltName(obj[2].toString());
			
			if(obj[3]!=null)assetStatus.setCreatedBy(Long.valueOf(obj[3].toString()));
			if(obj[4]!=null)assetStatus.setCreatedAt(Timestamp.valueOf(obj[4].toString()).toLocalDateTime());			
			
			if(obj[5]!=null) assetStatus.setUpdatedBy(Long.valueOf(obj[5].toString()));
			if(obj[6]!=null) assetStatus.setUpdatedAt(Timestamp.valueOf(obj[6].toString()).toLocalDateTime());
			
			assetStatus.setVersion(Integer.valueOf(obj[7].toString()));
			assetStatus.setIsActive(Boolean.valueOf(obj[8].toString()));
			assetStatuses.add(assetStatus);
		});
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
		String sql = "DELETE FROM asset_status WHERE id = :id";
		int result = em.createNativeQuery(sql)
						.setParameter("id", id)
						.executeUpdate();
		return result>0;
	}

}
