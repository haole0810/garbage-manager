package com.le.garbage_manager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bills")
@Getter @Setter @NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

    private LocalDate billingMonth; // Lưu ngày đầu tiên của tháng (VD: 2024-02-01)

    @Enumerated(EnumType.STRING)
    private BillStatus status = BillStatus.UNPAID;
}