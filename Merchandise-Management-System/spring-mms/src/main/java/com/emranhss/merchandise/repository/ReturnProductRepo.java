package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.ReturnProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnProductRepo extends JpaRepository<ReturnProduct,Long>{
}