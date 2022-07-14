package com.lawencon.asset.dao.impl.hibernatenative;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.CheckinDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Asset;
import com.lawencon.asset.model.AssetStatus;
import com.lawencon.asset.model.Checkin;
import com.lawencon.asset.model.Checkout;

@Profile("native")
@Repository
public class CheckinDaoHibernateNativeImpl extends BaseDaoImpl implements CheckinDao{

	@Override
	public List<Checkin> getAll() {
		String sql = "SELECT ci.id, ci.asset_status_id, ci.checkout_id, ci.created_by, ci.created_at, "
				+ "ci.updated_by, ci.updated_at, ci.version, ci.is_active, a.asset_name, ass.alt_name "
				+ "FROM checkin ci JOIN asset_status ass ON ci.asset_status_id = ass.id "
				+ "JOIN checkout co ON ci.checkout_id = co.id "
				+ "JOIN asset a ON co.asset_id = a.id ";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<Checkin> checkins = new ArrayList<Checkin>();
		results.forEach(result->{
			Checkin checkin = new Checkin();
			Object[] obj = (Object[]) result;
			checkin.setId(Long.valueOf(obj[0].toString()));
			AssetStatus assetStatus = new AssetStatus();
			assetStatus.setId(Long.valueOf(obj[1].toString()));
			assetStatus.setAltName(obj[10].toString());
			Checkout checkout = new Checkout();
			checkout.setId(Long.valueOf(obj[2].toString()));
			
			if(obj[3]!=null)checkin.setCreatedBy(Long.valueOf(obj[3].toString()));
			if(obj[4]!=null)checkin.setCreatedAt(Timestamp.valueOf(obj[4].toString()).toLocalDateTime());
			
			if(obj[5]!=null)checkin.setUpdatedBy(Long.valueOf(obj[5].toString()));
			if(obj[6]!=null)checkin.setUpdatedAt(Timestamp.valueOf(obj[6].toString()).toLocalDateTime());
			
			checkin.setVersion(Integer.valueOf(obj[7].toString()));
			checkin.setIsActive(Boolean.valueOf(obj[8].toString()));
			
			Asset asset = new Asset();
			asset.setAssetName(obj[9].toString());
			checkout.setAsset(asset);
			
			checkin.setAssetStatus(assetStatus);
			checkin.setCheckout(checkout);
			checkin.setCreatedBy(Long.valueOf(obj[3].toString()));			
			checkins.add(checkin);
		});
		return checkins;
	}

	@Override
	public Checkin getById(Long id) {
		Checkin checkin = new Checkin();
		try {
			checkin = em.find(Checkin.class, id);
			em.detach(checkin);	
		} catch (Exception e) {
			checkin = null;
		}		
		return checkin;
	}

	@Override
	public Checkin save(Checkin checkin) {
		if(checkin.getId()!=null) {
			checkin = em.merge(checkin);
			em.flush();
		}else {
			em.persist(checkin);
		}
		em.detach(checkin);
		return checkin;
	}

}
