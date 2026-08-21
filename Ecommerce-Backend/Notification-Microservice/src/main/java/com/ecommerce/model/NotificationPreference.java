package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification_preferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Builder.Default
    private Boolean emailEnabled = true;

    @Builder.Default
    private Boolean smsEnabled = true;

    @Builder.Default
    private Boolean pushEnabled = true;

    @Builder.Default
    private Boolean active = true;

    private Long createdBy;

    private Long updatedBy;

}