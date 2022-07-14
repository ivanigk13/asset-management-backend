package com.lawencon.asset.dao.impl.hibernatenative;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.CheckoutDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Asset;
import com.lawencon.asset.model.AssetStatus;
import com.lawencon.asset.model.AssetType;
import com.lawencon.asset.model.Checkout;
import com.lawencon.asset.model.Employee;

@Profile("native")
@Repository
public class CheckoutDaoHibernateNativeImpl extends BaseDaoImpl implements CheckoutDao {

	@Override
	public List<Checkout> getAll() {
		String sql = "SELECT co.id as first_id, a.asset_name as first_name, e.employee_name, aa.id as second_id, "
				+ "aa.asset_name as second_name, co.place, co.expired, co.created_by, "
				+ "co.created_at, co.updated_by, co.updated_at, co.version, co.is_active, "
				+ "ass.status_name, ass.alt_name, ast.type_name, a.id "
				+ "FROM checkout co LEFT JOIN asset a ON co.asset_id = a.id "
				+ "LEFT JOIN asset aa ON co.other_asset_id = aa.id "
				+ "LEFT JOIN employee e ON co.employee_id = e.id "
				+ "LEFT JOIN asset_type ast ON a.asset_type_id = ast.id "
				+ "LEFT JOIN asset_status ass ON a.asset_status_id = ass.id";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<Checkout> checkouts = new ArrayList<Checkout>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			Checkout checkout = new Checkout();
			checkout.setId(Long.valueOf(obj[0].toString()));
			
			Asset asset = new Asset();
			asset.setId(Long.valueOf(obj[16].toString()));
			asset.setAssetName(obj[1].toString());
			Employee employee = new Employee();
			if(obj[2]!=null) employee.setEmployeeName(obj[2].toString());
			else employee.setEmployeeName("-");
			Asset otherAsset = new Asset();
			if(obj[3]!=null) {
				otherAsset.setId(Long.valueOf(obj[3].toString()));
				otherAsset.setAssetName(obj[4].toString());
			}else {
				otherAsset.setId(null);
				otherAsset.setAssetName("-");
			}			
			if(obj[5]!=null)checkout.setPlace(obj[5].toString());
			else checkout.setPlace("-");
			if(obj[6]!=null) checkout.setExpired(((Date)obj[6]).toLocalDate());
			else checkout.setExpired(null);
			
			if(obj[7]!=null)checkout.setCreatedBy(Long.valueOf(obj[7].toString()));
			if(obj[8]!=null)checkout.setCreatedAt(Timestamp.valueOf(obj[8].toString()).toLocalDateTime());			
			
			if(obj[9]!=null) checkout.setUpdatedBy(Long.valueOf(obj[9].toString()));
			if(obj[10]!=null) checkout.setUpdatedAt(Timestamp.valueOf(obj[10].toString()).toLocalDateTime());
			
			checkout.setVersion(Integer.valueOf(obj[11].toString()));
			checkout.setIsActive(Boolean.valueOf(obj[12].toString()));
			
			AssetStatus assetStatus = new AssetStatus();
			assetStatus.setStatusName(obj[13].toString());
			assetStatus.setAltName(obj[14].toString());
			
			AssetType assetType = new AssetType();
			assetType.setTypeName(obj[15].toString());
			
			asset.setAssetStatus(assetStatus);
			asset.setAssetType(assetType);
			
			checkout.setAsset(asset);
			checkout.setEmployee(employee);
			checkout.setOtherAsset(otherAsset);
			
			checkouts.add(checkout);
		});
		return checkouts;
	}

	@Override
	public Checkout save(Checkout checkout) {
		if(checkout.getId()!=null) {
			checkout = em.merge(checkout);
			em.flush();
		}else {
			em.persist(checkout);
		}
		em.detach(checkout);
		return checkout;
	}

	@Override
	public Checkout getById(Long id) {
		Checkout checkout = em.find(Checkout.class, id);
		em.detach(checkout);
		return checkout;
	}

	@Override
	public List<Checkout> getUncheckin() {
		String sql = "SELECT co.id, a.id as assetId, a.asset_name, co.employee_id, co.other_asset_id, co.place, co.expired, co.created_by, "
				+ "co.created_at, co.updated_by, co.updated_at, co.version, co.is_active "
				+ "FROM checkout co JOIN asset a ON co.asset_id = a.id "
				+ "WHERE co.id NOT IN (SELECT checkout_id FROM checkin)";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<Checkout> checkouts = new ArrayList<Checkout>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			Checkout checkout = new Checkout();
			checkout.setId(Long.valueOf(obj[0].toString()));
			Asset asset = new Asset();
			asset.setId(Long.valueOf(obj[1].toString()));
			asset.setAssetName(obj[2].toString());
			
			Employee employee = new Employee();
			if(obj[3]!=null)employee.setId(Long.valueOf(obj[3].toString()));
			else employee.setId(null);
			
			Asset otherAsset = new Asset();
			if(obj[4]!=null)otherAsset.setId(Long.valueOf(obj[4].toString()));
			else otherAsset.setId(null);
			
			checkout.setAsset(asset);
			checkout.setEmployee(employee);
			checkout.setOtherAsset(otherAsset);
			if(obj[5]!=null)checkout.setPlace(obj[5].toString());
			else checkout.setPlace("-");
			if(obj[6]!=null)checkout.setExpired(((Date)obj[6]).toLocalDate());
			else checkout.setExpired(null);
			
			if(obj[7]!=null)checkout.setCreatedBy(Long.valueOf(obj[7].toString()));
			if(obj[8]!=null)checkout.setCreatedAt(Timestamp.valueOf(obj[8].toString()).toLocalDateTime());			
			
			if(obj[9]!=null) checkout.setUpdatedBy(Long.valueOf(obj[9].toString()));
			if(obj[10]!=null) checkout.setUpdatedAt(Timestamp.valueOf(obj[10].toString()).toLocalDateTime());
			
			checkout.setVersion(Integer.valueOf(obj[11].toString()));
			checkout.setIsActive(Boolean.valueOf(obj[12].toString()));
			checkouts.add(checkout);
		});
		return checkouts;
	}

	@Override
	public List<Checkout> getCheckoutExpired() {
		String sql = "SELECT co.id as checkoutId, a.asset_name as first_name, e.employee_name, aa.id as otherAssetId, "
				+ "aa.asset_name as second_name, co.place, co.expired, co.created_by, co.created_at, co.updated_by, "
				+ "co.updated_at, co.version, co.is_active, ass.status_name, ass.alt_name, ast.type_name, a.id "
				+ "FROM checkout co LEFT JOIN asset a ON co.asset_id = a.id "
				+ "LEFT JOIN asset aa ON co.other_asset_id = aa.id "
				+ "LEFT JOIN employee e ON co.employee_id = e.id "
				+ "LEFT JOIN asset_type ast ON a.asset_type_id = ast.id "
				+ "LEFT JOIN asset_status ass ON a.asset_status_id = ass.id "
				+ "WHERE co.expired <= NOW()";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<Checkout> checkouts = new ArrayList<Checkout>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			Checkout checkout = new Checkout();
			checkout.setId(Long.valueOf(obj[0].toString()));
			
			Asset asset = new Asset();
			asset.setId(Long.valueOf(obj[16].toString()));
			asset.setAssetName(obj[1].toString());
			
			Employee employee = new Employee();
			if(obj[2]!=null)employee.setEmployeeName(obj[2].toString());
			Asset otherAsset = new Asset();
			if(obj[3]!=null)otherAsset.setId(Long.valueOf(obj[3].toString()));
			if(obj[4]!=null)otherAsset.setAssetName(obj[4].toString());
			
			if(obj[5]!=null)checkout.setPlace(obj[5].toString());
			else checkout.setPlace("-");
			checkout.setExpired(((Date)obj[6]).toLocalDate());
			
			if(obj[7]!=null)checkout.setCreatedBy(Long.valueOf(obj[7].toString()));
			if(obj[8]!=null)checkout.setCreatedAt(Timestamp.valueOf(obj[8].toString()).toLocalDateTime());			
			
			if(obj[9]!=null) checkout.setUpdatedBy(Long.valueOf(obj[9].toString()));
			if(obj[10]!=null) checkout.setUpdatedAt(Timestamp.valueOf(obj[10].toString()).toLocalDateTime());
			
			checkout.setVersion(Integer.valueOf(obj[11].toString()));
			checkout.setIsActive(Boolean.valueOf(obj[12].toString()));
			
			AssetStatus assetStatus = new AssetStatus();
			assetStatus.setStatusName(obj[13].toString());
			assetStatus.setAltName(obj[14].toString());
			
			AssetType assetType = new AssetType();
			assetType.setTypeName(obj[15].toString());
			
			asset.setAssetStatus(assetStatus);
			asset.setAssetType(assetType);
			
			checkout.setAsset(asset);
			checkout.setEmployee(employee);
			checkout.setOtherAsset(otherAsset);
			
			checkouts.add(checkout);
		});
		return checkouts;
	}

}
