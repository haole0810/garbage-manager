package com.le.garbage_manager.repository;

import com.le.garbage_manager.entity.Property;
import com.le.garbage_manager.entity.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface IPropertyRepository extends JpaRepository<Property, Long> {

    // 1. Tìm Nhà riêng (hoặc Chung cư gốc) theo Tuyến đường
    @Query("SELECT p FROM Property p WHERE p.area.id = :areaId " +
            "AND p.type = :type " +
            "AND (:parentIsNull = true AND p.parent IS NULL OR :parentIsNull = false) " +
            "AND (:search IS NULL OR LOWER(p.address) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Property> findPropertiesWithFilter(
            @Param("areaId") Long areaId,
            @Param("type") PropertyType type,
            @Param("search") String search,
            @Param("parentIsNull") boolean parentIsNull,
            Pageable pageable);

    // 2. Tìm các đơn vị con (Phòng/Lầu) - Có thêm bộ lọc Floor
    @Query("SELECT p FROM Property p WHERE p.parent.id = :parentId " +
            "AND (:type IS NULL OR p.type = :type) " + // Thêm type để lọc đúng "BLOCK" hoặc "ROOM"
            "AND (:search IS NULL OR LOWER(p.address) LIKE LOWER(CONCAT('%', :search, '%')))" +
            "AND (:floor IS NULL OR p.floor = :floor)")
    Page<Property> findByParentWithFilter(
            @Param("parentId") Long parentId,
            @Param("type") PropertyType type,
            @Param("search") String search,
            @Param("floor") Integer floor,
            Pageable pageable);
    @Query("SELECT DISTINCT p.floor FROM Property p WHERE p.parent.id = :parentId AND p.floor IS NOT NULL ORDER BY p.floor ASC")
    List<Integer> findDistinctFloorsByParent(@Param("parentId") Long parentId);
}