package com.ecommerce.repository;

import com.ecommerce.model.Notification;
import com.ecommerce.model.enums.NotificationStatus;
import com.ecommerce.model.enums.NotificationType;
import com.ecommerce.model.enums.ReferenceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Page<Notification> findByUserId(Long userId, Pageable pageable);

    Page<Notification> findByStatus(NotificationStatus status, Pageable pageable);

    Page<Notification> findByNotificationType(NotificationType notificationType, Pageable pageable);

    Page<Notification> findByReferenceTypeAndReferenceId(
            ReferenceType referenceType,
            Long referenceId,
            Pageable pageable
    );

}