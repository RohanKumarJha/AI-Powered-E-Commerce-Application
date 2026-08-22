package com.ecommerce.mapper;

import com.ecommerce.dto.request.PaymentRequest;
import com.ecommerce.dto.response.PaymentResponse;
import com.ecommerce.model.Payment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PaymentMapper {


    private final ModelMapper modelMapper;


    public Payment toEntity(PaymentRequest request) {
        return modelMapper.map(
                request,
                Payment.class
        );
    }



    public PaymentResponse toResponse(Payment payment) {

        return modelMapper.map(
                payment,
                PaymentResponse.class
        );
    }

}