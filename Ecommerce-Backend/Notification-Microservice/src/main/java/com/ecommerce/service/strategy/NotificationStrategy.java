package com.ecommerce.service.strategy;

import com.ecommerce.model.Notification;

public interface NotificationStrategy {

    boolean send(Notification notification);

}