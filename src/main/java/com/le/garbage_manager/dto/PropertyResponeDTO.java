package com.le.garbage_manager.dto;

import com.le.garbage_manager.entity.PropertyType;
import lombok.Data;

@Data
public class PropertyResponeDTO {
    private Long id;
    private String addressNumber;
    private String ownerName;
    private Double totalDebt;
    private Integer floor;
}
