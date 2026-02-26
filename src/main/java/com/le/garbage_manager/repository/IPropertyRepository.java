package com.le.garbage_manager.repository;

import com.le.garbage_manager.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface IPropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByAreaId(Long areaId);
    List<Property> findByParentIsNull();
}
