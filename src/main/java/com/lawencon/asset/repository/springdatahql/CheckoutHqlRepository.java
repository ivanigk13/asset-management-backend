package com.lawencon.asset.repository.springdatahql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.model.Checkout;

@Repository
public interface CheckoutHqlRepository extends JpaRepository<Checkout, Long>{

	@Query(value="FROM Checkout WHERE id NOT IN (SELECT checkout.id FROM Checkin)")
	List<Checkout> getUncheckin();
	
	@Query(value="FROM Checkout WHERE expired <= CURRENT_TIMESTAMP")
	List<Checkout> getCheckoutExpired();
	
	@Query(value="SELECT co.id, co.asset.id, co.employee.id, co.otherAsset.id, co.place, co.expired, co.createdBy.id, "
			+ "co.createdAt, co.updatedBy.id, co.updatedAt, co.version, co.isActive "
			+ "FROM Checkout co WHERE co.id = :id AND id NOT IN (SELECT checkout.id FROM Checkin)")
	Checkout getCheckoutNotInCheckin(Long id);
	
	@Modifying
	int removeById(Long id);	
}
