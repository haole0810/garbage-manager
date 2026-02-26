package com.le.garbage_manager.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "properties")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address; // Số nhà
    private String owner;     // Tên chủ hộ
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private PropertyType type;    // Loại: HOUSE, APARTMENT_BUILDING, ROOM

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    // Quan hệ cha-con (Dùng cho Chung cư -> Phòng)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Property parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Property> subUnits;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Bill> bills;
}