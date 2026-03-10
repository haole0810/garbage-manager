package com.le.garbage_manager.service.impl;

import com.le.garbage_manager.dto.PropertyResponeDTO;
import com.le.garbage_manager.entity.Bill;
import com.le.garbage_manager.entity.BillStatus;
import com.le.garbage_manager.entity.Property;
import com.le.garbage_manager.entity.PropertyType;
import com.le.garbage_manager.repository.IPropertyRepository;
import com.le.garbage_manager.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class PropertyService implements IPropertyService {
    @Autowired
    private IPropertyRepository propertyRepository;
    private PropertyResponeDTO convertToDTO(Property pro) {
        PropertyResponeDTO dto = new PropertyResponeDTO();
        dto.setId(pro.getId());
        dto.setOwnerName(pro.getOwner());
        dto.setAddressNumber(pro.getAddress());
        dto.setFloor(pro.getFloor());
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
    public Page<PropertyResponeDTO> getHousesByArea(Long areaId, String search, Pageable pageable) {
        Page<Property> properties = propertyRepository.findByAreaIdAndTypeAndAddressContaining(
                areaId, PropertyType.HOUSE, search, pageable);

        return properties.map(this::convertToDTO);
    }

    @Override
    public Page<PropertyResponeDTO> getApartmentsByArea(Long areaId, String search, Pageable pageable) {
        return propertyRepository.findByAreaIdAndTypeAndParentIsNullAndAddressContaining(
                areaId, PropertyType.APARTMENT_BUILDING, search, pageable).map(this::convertToDTO);
    }

    @Override
    public Page<PropertyResponeDTO> getPropertiesByParent(Long parentId, String search, Integer floor, Pageable pageable) {
        // Nếu có floor (lầu) thì lọc theo lầu, nếu không thì hiện tất cả của parent đó
        // Ở đây thầy dùng phương thức findByParentIdAndAddressContaining mặc định của em
        return propertyRepository.findByParentWithFilter(parentId, search, floor, pageable)
                .map(this::convertToDTO);
    }
}
