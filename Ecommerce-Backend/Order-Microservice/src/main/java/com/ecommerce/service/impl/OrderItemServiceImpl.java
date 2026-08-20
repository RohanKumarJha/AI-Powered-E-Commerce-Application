package com.ecommerce.service.impl;

import com.ecommerce.dto.request.OrderItemRequest;
import com.ecommerce.dto.response.OrderItemResponse;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.OrderItemMapper;
import com.ecommerce.model.OrderItem;
import com.ecommerce.repository.OrderItemRepository;
import com.ecommerce.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemResponse addOrderItem(OrderItemRequest request) {
        throw new BadRequestException(
                "Order items are created automatically during checkout."
        );
    }

    @Override
    public List<OrderItemResponse> getItemsByOrderId(Long orderId) {
        if (orderId == null) {
            throw new BadRequestException(
                    "Order id cannot be null."
            );
        }
        return orderItemRepository
                .findByOrderOrderId(orderId)
                .stream()
                .map(orderItemMapper::toResponse)
                .toList();
    }

    @Override
    public OrderItemResponse getOrderItemById(Long orderItemId) {
        return orderItemMapper.toResponse(
                findOrderItemById(orderItemId)
        );
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        OrderItem orderItem = findOrderItemById(orderItemId);
        orderItemRepository.delete(orderItem);
    }

    private OrderItem findOrderItemById(Long orderItemId) {
        if (orderItemId == null) {
            throw new BadRequestException(
                    "Order item id cannot be null."
            );
        }
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "OrderItem",
                                "id",
                                orderItemId
                        )
                );
    }
}
