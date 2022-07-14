package com.lawencon.asset.dao.impl.hibernatenative;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.AssetTypeDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.AssetType;

@Profile("native")
@Repository
public class AssetTypeDaoHibernateNativeImpl extends BaseDaoImpl implements AssetTypeDao{

	@Override
	public List<AssetType> getAll() {
		String sql = "SELECT id, type_code, type_name, created_by, created_at, updated_by, updated_at, version, is_active "
				+ "FROM asset_type";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<AssetType> assetTypes = new ArrayList<AssetType>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			AssetType assetType = new AssetType();
			assetType.setId(Long.valueOf(obj[0].toString()));
			assetType.setTypeCode(obj[1].toString());
			assetType.setTypeName(obj[2].toString());
			
			if(obj[3]!=null)assetType.setCreatedBy(Long.valueOf(obj[3].toString()));
			if(obj[4]!=null)assetType.setCreatedAt(Timestamp.valueOf(obj[4].toString()).toLocalDateTime());			
			
			if(obj[5]!=null) assetType.setUpdatedBy(Long.valueOf(obj[5].toString()));
			if(obj[6]!=null) assetType.setUpdatedAt(Timestamp.valueOf(obj[6].toString()).toLocalDateTime());
			
			assetType.setVersion(Integer.valueOf(obj[7].toString()));
			assetType.setIsActive(Boolean.valueOf(obj[8].toString()));
			assetTypes.add(assetType);
		});
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
		String sql = "DELETE FROM asset_type WHERE id = :id";
		int result = em.createNativeQuery(sql)
						.setParameter("id", id)
						.executeUpdate();
		return result>0;
	}

}
