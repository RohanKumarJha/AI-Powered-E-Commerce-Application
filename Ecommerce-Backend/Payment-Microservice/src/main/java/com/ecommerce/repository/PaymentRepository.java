package com.ecommerce.repository;

import com.ecommerce.model.enums.PaymentStatus;
import com.ecommerce.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrderId(Long orderId);

    Page<Payment> findByUserId(Long userId, Pageable pageable);

    Page<Payment> findAll(Pageable pageable);

    boolean existsByOrderId(Long orderId);

    long countByPaymentStatus(PaymentStatus paymentStatus);

    @Query("""
       SELECT COALESCE(SUM(p.amount),0)
       FROM Payment p
       WHERE p.paymentStatus='SUCCESS'
       """)
    BigDecimal getTotalRevenue();

    @Query("""
       SELECT COALESCE(SUM(p.refundAmount),0)
       FROM Payment p
       """)
    BigDecimal getTotalRefundAmount();

}