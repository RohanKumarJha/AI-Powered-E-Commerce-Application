package com.ecommerce.service.impl;

import com.ecommerce.dto.request.AddressRequest;
import com.ecommerce.dto.response.AddressResponse;
import com.ecommerce.mapper.AddressMapper;
import com.ecommerce.model.Address;
import com.ecommerce.model.User;
import com.ecommerce.repository.AddressRepository;
import com.ecommerce.service.AddressService;
import com.ecommerce.service.factory.AddressFactory;
import com.ecommerce.service.factory.UserFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;
    private final AddressFactory addressFactory;
    private final UserFactory userFactory;

    @Override
    public AddressResponse addAddress(
            Long userId,
            AddressRequest request) {
        User user = userFactory.getUserById(userId);
        if (Boolean.TRUE.equals(request.getIsDefault())) {
            unsetDefaultAddress(userId);
        }
        Address address = addressFactory.createAddress(request);
        address.setUser(user);
        Address savedAddress = addressRepository.save(address);
        log.info(
                "Saved address successfully for userId : {}",
                userId
        );
        return addressMapper.toResponse(savedAddress);
    }

    @Override
    public AddressResponse updateAddress(
            Long addressId,
            AddressRequest request) {
        Address address = addressFactory.getAddressById(addressId);
        if (Boolean.TRUE.equals(request.getIsDefault())) {
            unsetDefaultAddress(address.getUser().getUserId(), addressId);
        }
        addressMapper.updateEntity(request, address);
        Address updatedAddress = addressRepository.save(address);
        log.info(
                "Updated address successfully with addressId : {}",
                addressId
        );
        return addressMapper.toResponse(updatedAddress);
    }

    @Override
    public void deleteAddress(Long addressId) {
        Address address = addressFactory.getAddressById(addressId);
        addressRepository.delete(address);
        log.info(
                "Deleted address successfully with addressId : {}",
                addressId
        );
    }

    @Override
    public AddressResponse getAddressById(Long addressId) {
        Address address = addressFactory.getAddressById(addressId);
        log.info(
                "Get address successfully with addressId : {}",
                addressId
        );
        return addressMapper.toResponse(address);
    }

    @Override
    public List<AddressResponse> getUserAddresses(Long userId) {
        User user = userFactory.getUserById(userId);
        List<AddressResponse> addressResponses =
                addressRepository.findByUser(user)
                        .stream()
                        .map(addressMapper::toResponse)
                        .toList();
        log.info(
                "Get all addresses successfully for userId : {}",
                userId
        );
        return addressResponses;
    }


//  ------------------------------- Optional ---------------------------------------
    private void unsetDefaultAddress(Long userId) {
        addressRepository
                .findByUserUserIdAndIsDefaultTrue(userId)
                .ifPresent(address -> {
                    address.setIsDefault(false);
                    addressRepository.save(address);
                });
    }

    private void unsetDefaultAddress(
            Long userId,
            Long addressId) {
        addressRepository
                .findByUserUserIdAndIsDefaultTrue(userId)
                .ifPresent(address -> {
                    if (!address.getAddressId().equals(addressId)) {
                        address.setIsDefault(false);
                        addressRepository.save(address);
                    }
                });
    }
}