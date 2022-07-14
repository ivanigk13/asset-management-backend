package com.lawencon.asset.dao.impl.hibernatenative;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.CompanyDao;
import com.lawencon.asset.dao.impl.BaseDaoImpl;
import com.lawencon.asset.model.Company;

@Profile("native")
@Repository
public class CompanyDaoHibernateNativeImpl extends BaseDaoImpl implements CompanyDao{

	@Override
	public List<Company> getAll() {
		String sql = "SELECT id, company_name, company_address, created_by, created_at, updated_by, updated_at, version, is_active "
				+ "FROM company";
		List<?> results = em.createNativeQuery(sql).getResultList();
		List<Company> companies = new ArrayList<>();
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			Company company = new Company();
			company.setId(Long.valueOf(obj[0].toString()));
			company.setCompanyName(obj[1].toString());
			company.setCompanyAddress(obj[2].toString());
			
			if(obj[3]!=null)company.setCreatedBy(Long.valueOf(obj[3].toString()));
			if(obj[4]!=null)company.setCreatedAt(Timestamp.valueOf(obj[4].toString()).toLocalDateTime());			
			
			if(obj[5]!=null) company.setUpdatedBy(Long.valueOf(obj[5].toString()));
			if(obj[6]!=null) company.setUpdatedAt(Timestamp.valueOf(obj[6].toString()).toLocalDateTime());

			company.setVersion(Integer.valueOf(obj[7].toString()));
			company.setIsActive(Boolean.valueOf(obj[8].toString()));
			companies.add(company);
		});
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
		Company company = new Company();
		try {
			company = em.find(Company.class, id);
			em.detach(company);
		} catch (Exception e) {
			e.printStackTrace();
			company = null;
		}		
		return company;
	}

	@Override
	public boolean delete(Long id) {
		String sql = "DELETE FROM company WHERE id = :id";
		int result = em.createNativeQuery(sql)
						.setParameter("id", id)
						.executeUpdate();
		return result>0;
	}

}
