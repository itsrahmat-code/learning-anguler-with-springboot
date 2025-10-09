package com.emranhss.merchandise.repository;


import com.emranhss.merchandise.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice,Long> {
    // salesdashboard
    @Query("SELECT COALESCE(SUM(i.total), 0) " +
            "FROM Invoice i " +
            "WHERE i.date BETWEEN :start AND :end")
    Double getSalesBetween(@Param("start") LocalDateTime start,
                           @Param("end") LocalDateTime end);

    // salesdashboard end

    // Total sales for today
//    @Query("SELECT COALESCE(SUM(i.total), 0) " +
//            "FROM Invoice i " +
//            "WHERE DATE(i.date) = CURRENT_DATE")
//    Double getTodaySales();
//
//    // Total sales in last 7 days
//    @Query("SELECT COALESCE(SUM(i.total), 0) " +
//            "FROM Invoice i " +
//            "WHERE i.date >= CURRENT_DATE - 7")
//    Double getLast7DaysSales();
//
//    // Total sales in last 30 days
//    @Query("SELECT COALESCE(SUM(i.total), 0) " +
//            "FROM Invoice i " +
//            "WHERE i.date >= CURRENT_DATE - 30")
//    Double getLast30DaysSales();




}
