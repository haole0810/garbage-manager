package com.le.garbage_manager.service.impl;

import com.le.garbage_manager.dto.AreaResponeDTO;
import com.le.garbage_manager.repository.IAreaRepository;
import com.le.garbage_manager.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AreaService implements IAreaService {
    @Autowired
    private IAreaRepository areaRepository;

    @Override
    public List<AreaResponeDTO> getAllAreas() {
        return areaRepository.findAll().stream().map(area -> {
            AreaResponeDTO dto = new AreaResponeDTO();
            dto.setId(area.getId());
            dto.setName(area.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
