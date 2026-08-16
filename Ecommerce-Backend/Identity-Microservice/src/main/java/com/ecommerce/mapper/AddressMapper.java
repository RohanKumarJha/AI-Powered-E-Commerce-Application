package com.ecommerce.mapper;

import com.ecommerce.dto.request.AddressRequest;
import com.ecommerce.dto.response.AddressResponse;
import com.ecommerce.model.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    private final ModelMapper modelMapper;

    public Address toEntity(AddressRequest request) {
        return modelMapper.map(request, Address.class);
    }

    public AddressResponse toResponse(Address address) {
        return modelMapper.map(address, AddressResponse.class);
    }

    public void updateEntity(AddressRequest request, Address address) {
        modelMapper.map(request, address);
    }
}
