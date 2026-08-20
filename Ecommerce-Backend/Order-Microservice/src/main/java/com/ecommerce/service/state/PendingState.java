package com.ecommerce.service.state;

import com.ecommerce.model.enums.OrderStatus;
import com.ecommerce.model.Order;
import org.springframework.stereotype.Component;

@Component
public class PendingState implements OrderState {

    @Override
    public void handle(Order order) {
        order.setStatus(OrderStatus.PENDING);
    }
}