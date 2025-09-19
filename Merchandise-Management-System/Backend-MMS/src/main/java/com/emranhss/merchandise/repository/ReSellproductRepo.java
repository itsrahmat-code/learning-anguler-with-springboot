package com.emranhss.merchandise.repository;
import com.emranhss.merchandise.entity.ReSellProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReSellproductRepo extends JpaRepository<ReSellProduct,Long> {
}
