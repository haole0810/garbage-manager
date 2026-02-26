package com.le.garbage_manager.controller;

import com.le.garbage_manager.dto.AreaResponeDTO;
import com.le.garbage_manager.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
@CrossOrigin(origins = "http://localhost:5173") // Cho phép React truy cập
public class AreaController {

    @Autowired
    private IAreaService areaService;

    @GetMapping
    public ResponseEntity<List<AreaResponeDTO>> getAllAreas() {
        List<AreaResponeDTO> areas = areaService.getAllAreas();
        return ResponseEntity.ok(areas);
    }
}