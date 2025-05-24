package com.medecineWebApp.Inventory_Service.service.impl;

import com.medecineWebApp.Inventory_Service.dto.InventoryItemDTO;
import com.medecineWebApp.Inventory_Service.dto.InventoryRecordDTO;
import com.medecineWebApp.Inventory_Service.dto.PurchaseItemDTO;
import com.medecineWebApp.Inventory_Service.enums.InventoryStatus;
import com.medecineWebApp.Inventory_Service.mapper.InventoryItemMapper;
import com.medecineWebApp.Inventory_Service.mapper.InventoryRecordMapper;
import com.medecineWebApp.Inventory_Service.models.InventoryCategory;
import com.medecineWebApp.Inventory_Service.models.InventoryItem;
import com.medecineWebApp.Inventory_Service.models.InventoryRecord;
import com.medecineWebApp.Inventory_Service.playload.InventoryItemRequest;
import com.medecineWebApp.Inventory_Service.playload.InventoryRequest;
import com.medecineWebApp.Inventory_Service.repository.InventoryCategoryRepository;
import com.medecineWebApp.Inventory_Service.repository.InventoryItemRepository;
import com.medecineWebApp.Inventory_Service.repository.InventoryRecordRepository;
import com.medecineWebApp.Inventory_Service.service.InventoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryRecordRepository inventoryRecordRepository;
    private final InventoryItemMapper inventoryItemMapper;
    private final InventoryRecordMapper inventoryRecordMapper;
    private final InventoryCategoryRepository InventoryCategoryRepository;

    public InventoryServiceImpl(InventoryItemRepository inventoryItemRepository, InventoryRecordRepository inventoryRecordRepository, InventoryItemMapper inventoryItemMapper, InventoryRecordMapper inventoryRecordMapper, InventoryCategoryRepository inventoryCategoryRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.inventoryRecordRepository = inventoryRecordRepository;
        this.inventoryItemMapper = inventoryItemMapper;
        this.inventoryRecordMapper = inventoryRecordMapper;
        InventoryCategoryRepository = inventoryCategoryRepository;
    }

    @Override
    public InventoryRecordDTO recordInventory(InventoryRequest request) {
        InventoryItem item = inventoryItemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("Article non trouvé"));



        InventoryRecord record = new InventoryRecord();
        record.setItem(item);
        record.setCountedQuantity(request.getCountedQuantity());
        record.setCheckedAt(LocalDateTime.now());
        record.setCheckedBy(request.getCheckedBy());
        // Détermination du statut
        if (request.getCountedQuantity() == item.getExpectedQuantity()) {
            record.setStatus(InventoryStatus.OK);
        } else if (request.getCountedQuantity() == 0) {
            record.setStatus(InventoryStatus.MANQUANT);
        } else if (request.getCountedQuantity() < item.getExpectedQuantity()) {
            record.setStatus(InventoryStatus.ENDOMMAGE);
        }
        // Mise à jour de la quantité réelle dans l'inventaire
        item.setActualQuantity(request.getCountedQuantity());
        inventoryItemRepository.save(item);

        return inventoryRecordMapper.mapToDto(inventoryRecordRepository.save(record));

    }

    @Override
    public List<InventoryRecordDTO> getInventoryHistory(LocalDateTime start, LocalDateTime end) {
        return inventoryRecordRepository.findByCheckedAtBetween(start, end).stream()
                .map(inventoryRecordMapper::mapToDto)
                .toList();
    }

    @Override
    public InventoryItemDTO addInventoryItem(InventoryItemRequest request) {
        // Vérifier si la catégorie existe déjà, sinon la créer
        InventoryCategory category = InventoryCategoryRepository.findByName(request.getCategoryName())
                .orElseGet(() -> InventoryCategoryRepository.save(new InventoryCategory(request.getCategoryName())));
        InventoryItem item = new InventoryItem();
        item.setItemName(request.getName());
        item.setExpectedQuantity(request.getExpectedQuantity());
        item.setCategory(category);

        return inventoryItemMapper.toDTO(inventoryItemRepository.save(item));
    }

    @Override
    public void restock(List<PurchaseItemDTO> items) {
        for (PurchaseItemDTO item : items) {
            InventoryItem invItem = inventoryItemRepository
                    .findByItemNameIgnoreCase(item.getItemName())
                    .orElseGet(() -> new InventoryItem());
            invItem.setQuantity(invItem.getQuantity() + item.getQuantity());
           // invItem.setLastRestockedDate(LocalDate.now());
            inventoryItemRepository.save(invItem);
        }
    }


}
