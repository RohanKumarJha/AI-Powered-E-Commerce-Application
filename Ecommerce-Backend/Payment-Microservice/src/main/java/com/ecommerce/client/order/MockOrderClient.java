package com.ecommerce.client.order;

import com.ecommerce.client.order.response.MockOrderResponse;
import com.ecommerce.client.order.response.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class MockOrderClient implements OrderClient {

    @Override
    public OrderResponse getOrderById(Long orderId) {

        return MockOrderResponse.getOrder(orderId);

    }

}