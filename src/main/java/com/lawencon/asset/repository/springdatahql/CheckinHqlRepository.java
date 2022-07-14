package com.lawencon.asset.repository.springdatahql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.model.Checkin;

@Repository
public interface CheckinHqlRepository extends JpaRepository<Checkin, Long>{

}
