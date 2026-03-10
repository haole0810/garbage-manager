package com.le.garbage_manager.controller;

import com.le.garbage_manager.dto.PropertyResponeDTO;
import com.le.garbage_manager.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "http://localhost:5173")
public class PropertyController {

    @Autowired
    private IPropertyService propertyService;

    @GetMapping("/area/{areaId}/houses")
    public ResponseEntity<List<PropertyResponeDTO>> getHousesByArea(@PathVariable Long areaId) {
        return ResponseEntity.ok(propertyService.getHousesByArea(areaId));
    }

    // Lấy danh sách Chung cư (Chỉ lấy các tòa nhà gốc)
    @GetMapping("/area/{areaId}/apartments")
    public ResponseEntity<List<PropertyResponeDTO>> getApartmentsByArea(@PathVariable Long areaId) {
        return ResponseEntity.ok(propertyService.getApartmentsByArea(areaId));
    }

    // Giữ nguyên để lấy các Block/Tầng/Phòng của chung cư
    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<PropertyResponeDTO>> getSubUnits(@PathVariable Long parentId) {
        return ResponseEntity.ok(propertyService.getPropertiesByParent(parentId));
    }
}