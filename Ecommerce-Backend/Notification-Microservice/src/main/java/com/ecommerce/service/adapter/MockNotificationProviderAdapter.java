package com.ecommerce.service.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MockNotificationProviderAdapter implements NotificationProviderAdapter {

    @Override
    public boolean sendEmail(
            String recipient,
            String subject,
            String message) {
        log.info("Mock Email Sent To: {}", recipient);
        log.info("Subject: {}", subject);
        log.info("Message: {}", message);
        return true;
    }

    @Override
    public boolean sendSms(
            String recipient,
            String message) {
        log.info("Mock SMS Sent To: {}", recipient);
        log.info("Message: {}", message);
        return true;
    }

    @Override
    public boolean sendPush(
            String recipient,
            String message) {
        log.info("Mock Push Notification Sent To: {}", recipient);
        log.info("Message: {}", message);
        return true;
    }
}