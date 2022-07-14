package com.lawencon.asset.dao.impl.hibernatehql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.CheckinDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Checkin;

@Profile("hql")
@Repository
public class CheckinDaoHibernateHqlImpl extends BaseDaoImpl implements CheckinDao{

	@Override
	public List<Checkin> getAll() {
		String sql = "SELECT ci FROM Checkin ci "
				+ "INNER JOIN FETCH ci.checkout as co "
				+ "INNER JOIN FETCH ci.assetStatus as ass "
				+ "ORDER BY ci.id";
		List<Checkin> checkins = em.createQuery(sql, Checkin.class).getResultList();
		return checkins;
	}

	@Override
	public Checkin getById(Long id) {
		Checkin checkin = em.find(Checkin.class, id);
		em.detach(checkin);
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
