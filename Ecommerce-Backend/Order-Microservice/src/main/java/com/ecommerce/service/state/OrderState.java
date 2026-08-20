package com.ecommerce.service.state;

import com.ecommerce.model.Order;

public interface OrderState {

    void handle(Order order);

}