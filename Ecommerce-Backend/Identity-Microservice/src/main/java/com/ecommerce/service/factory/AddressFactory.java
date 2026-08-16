package com.ecommerce.service.factory;

import com.ecommerce.dto.request.AddressRequest;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.AddressMapper;
import com.ecommerce.model.Address;
import com.ecommerce.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddressFactory {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public Address createAddress(AddressRequest request) {
        log.debug("Creating address entity for request.");
        return addressMapper.toEntity(request);
    }

    public Address getAddressById(Long addressId) {
        log.debug("Fetching address by id : {}",addressId);
        return addressRepository.findById(addressId)
                .orElseThrow(() -> {
                    log.warn("Address not found with id : {}",addressId);
                    return new ResourceNotFoundException(
                            "Address",
                            "addressId",
                            addressId
                    );
                });
    }
}
