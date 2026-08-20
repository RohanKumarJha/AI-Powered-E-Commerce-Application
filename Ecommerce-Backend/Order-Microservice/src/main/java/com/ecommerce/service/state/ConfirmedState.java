package com.ecommerce.service.state;

import com.ecommerce.model.Order;
import com.ecommerce.model.enums.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class ConfirmedState implements OrderState {

    @Override
    public void handle(Order order) {
        order.setStatus(OrderStatus.CONFIRMED);
    }
}