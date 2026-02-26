package com.le.garbage_manager.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private LocalDate billingMonth;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private BillStatus status = BillStatus.UNPAID;

    // --- PHẦN QUAN TRỌNG CHO LỊCH SỬ THU TIỀN ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collector_id")
    private User collector; // Ai là người thu? (Tự động lấy từ User đang login)

    private LocalDateTime paymentTime; // Thu vào lúc nào?


}