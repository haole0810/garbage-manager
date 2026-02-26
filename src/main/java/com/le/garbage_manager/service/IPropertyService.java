package com.le.garbage_manager.service;

import com.le.garbage_manager.dto.PropertyResponeDTO;

import java.util.List;

public interface IPropertyService {
    List<PropertyResponeDTO> getPropertiesByArea(Long areaId);
    List<PropertyResponeDTO> getBuildingsByArea(Long areaId);
    List<PropertyResponeDTO> getPropertiesByParent(Long parentId);

}
