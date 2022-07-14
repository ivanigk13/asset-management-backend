package com.lawencon.asset.dao.impl.hibernatehql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.CheckoutDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Asset;
import com.lawencon.asset.model.Checkout;
import com.lawencon.asset.model.Employee;

@Profile("hql")
@Repository
public class CheckoutDaoHibernateHqlImpl extends BaseDaoImpl implements CheckoutDao {

	@Override
	public List<Checkout> getAll() {
		String sql = "SELECT c FROM Checkout c "
				+ "LEFT JOIN FETCH c.asset as a "
				+ "LEFT JOIN FETCH a.brand "
				+ "LEFT JOIN FETCH a.company "
				+ "LEFT JOIN FETCH a.assetType "
				+ "LEFT JOIN FETCH a.assetStatus "
				+ "LEFT JOIN FETCH a.file "
				+ "LEFT JOIN FETCH c.employee as e "
				+ "LEFT JOIN FETCH e.company "
				+ "LEFT JOIN FETCH e.user as u "
				+ "LEFT JOIN FETCH u.role "
				+ "LEFT JOIN FETCH c.otherAsset as aa "
				+ "LEFT JOIN FETCH aa.brand "
				+ "LEFT JOIN FETCH aa.company "
				+ "LEFT JOIN FETCH aa.assetType "
				+ "LEFT JOIN FETCH aa.assetStatus "
				+ "LEFT JOIN FETCH aa.file "
				+ "ORDER BY c.id ASC";
		List<Checkout> checkouts = em.createQuery(sql, Checkout.class).getResultList();
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
		String sql = "SELECT co.id, co.asset.id, co.asset.assetName, co.employee.id, co.otherAsset.id, co.place, co.expired, "
				+ "co.createdBy.id, co.createdAt, co.updatedBy.id, co.updatedAt, co.version, co.isActive, "
				+ "co.employee.employeeName, co.otherAsset.assetName "
				+ "FROM Checkout co "
				+ "LEFT JOIN co.asset "
				+ "LEFT JOIN co.employee "
				+ "LEFT JOIN co.otherAsset "
				+ "WHERE co.id NOT IN (SELECT checkout.id FROM Checkin)";
		
		List<?> results = em.createQuery(sql).getResultList();
		List<Checkout> checkouts = new ArrayList<Checkout>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			Checkout checkout = new Checkout();
			checkout.setId(Long.valueOf(obj[0].toString()));
			Asset asset = new Asset();
			asset.setId(Long.valueOf(obj[1].toString()));
			asset.setAssetName(obj[2].toString());
			
			Employee employee = new Employee();
			if(obj[3]!=null) {
				employee.setId(Long.valueOf(obj[3].toString()));
				employee.setEmployeeName(obj[13].toString());
			}
			
			Asset otherAsset = new Asset();
			if(obj[4]!=null) {
				otherAsset.setId(Long.valueOf(obj[4].toString()));
				otherAsset.setAssetName(obj[14].toString());
			}
			
			checkout.setAsset(asset);
			checkout.setEmployee(employee);
			checkout.setOtherAsset(otherAsset);
			if(obj[5]!=null)checkout.setPlace(obj[5].toString());
			else checkout.setPlace("-");
			if(obj[6]!=null)checkout.setExpired(((LocalDate)obj[6]));
			else checkout.setExpired(null);
			
			if(obj[7]!=null)checkout.setCreatedBy(Long.valueOf(obj[7].toString()));
			if(obj[8]!=null)checkout.setCreatedAt(((LocalDateTime)obj[8]));			
			
			if(obj[9]!=null) checkout.setUpdatedBy(Long.valueOf(obj[9].toString()));
			if(obj[10]!=null) checkout.setUpdatedAt(((LocalDateTime)obj[10]));
			
			checkout.setVersion(Integer.valueOf(obj[11].toString()));
			checkout.setIsActive(Boolean.valueOf(obj[12].toString()));
			checkouts.add(checkout);
		});
		return checkouts;
	}

	@Override
	public List<Checkout> getCheckoutExpired() {
		String sql = "SELECT c FROM Checkout c "
				+ "LEFT JOIN FETCH c.asset as a "
				+ "LEFT JOIN FETCH a.brand "
				+ "LEFT JOIN FETCH a.company "
				+ "LEFT JOIN FETCH a.assetType "
				+ "LEFT JOIN FETCH a.assetStatus "
				+ "LEFT JOIN FETCH a.file "
				+ "LEFT JOIN FETCH c.employee as e "
				+ "LEFT JOIN FETCH e.company "
				+ "LEFT JOIN FETCH c.otherAsset as aa "
				+ "LEFT JOIN FETCH aa.brand "
				+ "LEFT JOIN FETCH aa.company "
				+ "LEFT JOIN FETCH aa.assetType "
				+ "LEFT JOIN FETCH aa.assetStatus "
				+ "LEFT JOIN FETCH aa.file "
				+ "WHERE c.expired <= CURRENT_TIMESTAMP";
		List<Checkout> checkouts = em.createQuery(sql, Checkout.class).getResultList();
		return checkouts;
	}

}
