package com.ecommerce.service.adapter;

public interface NotificationProviderAdapter {


    boolean sendEmail(
            String recipient,
            String subject,
            String message
    );


    boolean sendSms(
            String recipient,
            String message
    );


    boolean sendPush(
            String recipient,
            String message
    );

}