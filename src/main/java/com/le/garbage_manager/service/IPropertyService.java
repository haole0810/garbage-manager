package com.le.garbage_manager.service;

import com.le.garbage_manager.dto.PropertyResponeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPropertyService {
    // Thêm tham số search và pageable
    Page<PropertyResponeDTO> getHousesByArea(Long areaId, String search, Pageable pageable);

    Page<PropertyResponeDTO> getApartmentsByArea(Long areaId, String search, Pageable pageable);

    // Thêm floor để lọc theo lầu cho chung cư
    Page<PropertyResponeDTO> getPropertiesByParent(Long parentId, String search, Integer floor, Pageable pageable);
}
