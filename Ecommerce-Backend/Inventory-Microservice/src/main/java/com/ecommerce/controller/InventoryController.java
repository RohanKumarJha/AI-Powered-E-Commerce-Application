package com.ecommerce.controller;

import com.ecommerce.dto.request.InventoryRequest;
import com.ecommerce.dto.request.UpdateInventoryRequest;
import com.ecommerce.dto.request.UpdateStockRequest;
import com.ecommerce.dto.response.InventoryResponse;
import com.ecommerce.dto.response.PageResponse;
import com.ecommerce.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(
            @Valid @RequestBody InventoryRequest request) {
        log.info("Receive request for creating inventory for product with productId : {}",
                request.getProductId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inventoryService.createInventory(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<InventoryResponse>> getAllInventories(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir) {
        log.info("Receive request for get all inventories");
        return ResponseEntity.ok(
                inventoryService.getAllInventories(
                        page,
                        size,
                        sortBy,
                        sortDir
                )
        );
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryResponse> getInventoryById(
            @PathVariable Long inventoryId) {
        log.info("Receive request for getting inventory with inventoryId : {}",
                inventoryId);
        return ResponseEntity.ok(
                inventoryService.getInventoryById(inventoryId)
        );
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<InventoryResponse> getInventoryByProductId(
            @PathVariable Long productId) {
        log.info("Receive request for getting inventory with productId : {}",
                productId);
        return ResponseEntity.ok(
                inventoryService.getInventoryByProductId(productId)
        );
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<InventoryResponse> updateInventory(
            @PathVariable Long inventoryId,
            @Valid @RequestBody UpdateInventoryRequest request) {
        log.info("Receive request for update inventory with inventoryId : {}",
                inventoryId);
        return ResponseEntity.ok(
                inventoryService.updateInventory(
                        inventoryId,
                        request
                )
        );
    }

    @PatchMapping("/{inventoryId}/stock")
    public ResponseEntity<InventoryResponse> updateStock(
            @PathVariable Long inventoryId,
            @Valid @RequestBody UpdateStockRequest request) {
        log.info("Receive request for updating stock on inventory with inventoryId : {}",
                inventoryId);
        return ResponseEntity.ok(
                inventoryService.updateStock(
                        inventoryId,
                        request
                )
        );
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<Void> deleteInventory(
            @PathVariable Long inventoryId) {
        log.info("Receive request for delete stock on inventory with inventoryId : {}",
                inventoryId);
        inventoryService.deleteInventory(inventoryId);
        return ResponseEntity.noContent().build();
    }
}