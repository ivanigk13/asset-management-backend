package com.lawencon.asset.dao.impl.hibernatehql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.CompanyDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Company;

@Profile("hql")
@Repository
public class CompanyDaoHibernateHqlImpl extends BaseDaoImpl implements CompanyDao{

	@Override
	public List<Company> getAll() {
		String sql = "FROM Company";
		List<Company> companies = em.createQuery(sql, Company.class).getResultList();
		return companies;
	}

	@Override
	public Company save(Company company) {
		if(company.getId()!=null) {
			company = em.merge(company);
			em.flush();
		}else {
			em.persist(company);
		}
		em.detach(company);
		return company;
	}

	@Override
	public Company getById(Long id) {
		Company company = em.find(Company.class, id);
		em.detach(company);	
		return company;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM Company WHERE id = :id";
		int result = em.createQuery(sql)
						.setParameter("id", id)
						.executeUpdate();
		return result>0;
	}

}
