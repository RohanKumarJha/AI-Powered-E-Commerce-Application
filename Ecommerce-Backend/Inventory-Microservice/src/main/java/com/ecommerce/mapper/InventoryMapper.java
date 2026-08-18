package com.ecommerce.mapper;

import com.ecommerce.dto.request.InventoryRequest;
import com.ecommerce.dto.request.UpdateInventoryRequest;
import com.ecommerce.dto.response.InventoryResponse;
import com.ecommerce.model.Inventory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    private final ModelMapper modelMapper;

    public InventoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Inventory toEntity(InventoryRequest request) {
        return modelMapper.map(request, Inventory.class);
    }

    public InventoryResponse toResponse(Inventory inventory) {
        return modelMapper.map(inventory, InventoryResponse.class);
    }

    public void updateEntity(UpdateInventoryRequest request,
                             Inventory inventory) {
        modelMapper.map(request, inventory);
    }
}