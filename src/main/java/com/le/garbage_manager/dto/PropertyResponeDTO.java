package com.le.garbage_manager.dto;

import com.le.garbage_manager.entity.PropertyType;
import lombok.Data;

@Data
public class PropertyResponeDTO {
    private Long id;
    private String addressNumber;
    private String ownerName;
    private PropertyType type;
    private String areaName;
    private Double totalDebt;
}
