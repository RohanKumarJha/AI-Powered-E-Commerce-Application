package com.ecommerce.service;

import com.ecommerce.dto.request.AddressRequest;
import com.ecommerce.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    AddressResponse addAddress(Long userId, AddressRequest request);

    AddressResponse updateAddress(Long addressId, AddressRequest request);

    void deleteAddress(Long addressId);

    AddressResponse getAddressById(Long addressId);

    List<AddressResponse> getUserAddresses(Long userId);

}