package com.le.garbage_manager.repository;

import com.le.garbage_manager.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAreaRepository extends JpaRepository<Area, Long> {
}
