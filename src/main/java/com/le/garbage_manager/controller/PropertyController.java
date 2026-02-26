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

    @GetMapping("/area/{areaId}")
    public ResponseEntity<List<PropertyResponeDTO>> getPropertiesByArea(@PathVariable Long areaId) {
        List<PropertyResponeDTO> properties = propertyService.getPropertiesByArea(areaId);
        return ResponseEntity.ok(properties);
    }
    @GetMapping("/area/{areaId}/buildings")
    public ResponseEntity<List<PropertyResponeDTO>> getBuildingsByArea(@PathVariable Long areaId) {
        return ResponseEntity.ok(propertyService.getBuildingsByArea(areaId));
    }
    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<PropertyResponeDTO>> getSubUnits(@PathVariable Long parentId) {
        return ResponseEntity.ok(propertyService.getPropertiesByParent(parentId));    }
}