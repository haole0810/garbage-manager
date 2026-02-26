package com.le.garbage_manager.service.impl;

import com.le.garbage_manager.dto.PropertyResponeDTO;
import com.le.garbage_manager.entity.Bill;
import com.le.garbage_manager.entity.BillStatus;
import com.le.garbage_manager.entity.Property;
import com.le.garbage_manager.repository.IPropertyRepository;
import com.le.garbage_manager.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService implements IPropertyService {
    @Autowired
    private IPropertyRepository propertyRepository;
    private PropertyResponeDTO convertToDTO(Property pro) {
        PropertyResponeDTO dto = new PropertyResponeDTO();
        dto.setId(pro.getId());
        dto.setOwnerName(pro.getOwner());
        dto.setType(pro.getType());
        dto.setAreaName(pro.getArea().getName());

        if(pro.getBills()!=null){
            double deblt=pro.getBills().stream()
                    .filter(b->b.getStatus()==BillStatus.UNPAID)
                    .mapToDouble(Bill::getAmount)
                    .sum();
            dto.setTotalDebt(deblt);
        }else{
            dto.setTotalDebt(0.0);
        }
        return dto;
    }
    @Override
    public List<PropertyResponeDTO> getPropertiesByArea(Long areaId) {
        return propertyRepository.findByAreaId(areaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<PropertyResponeDTO> getBuildingsByArea(Long areaId) {
        return propertyRepository.findByAreaId(areaId).stream()
                .filter(p -> p.getParent() == null)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }@Override
    public List<PropertyResponeDTO> getPropertiesByParent(Long parentId) {
        return propertyRepository.findAll().stream()
                .filter(p -> p.getParent() != null && p.getParent().getId().equals(parentId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
