package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.ReInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReInvoiceRepo extends JpaRepository<ReInvoice,Long> {

}
