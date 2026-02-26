package com.le.garbage_manager.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // Tên đăng nhập

    @Column(nullable = false)
    private String password; // Mật khẩu (sau này sẽ mã hóa)

    private String fullName; // Tên đầy đủ (VD: Nguyễn Văn Hào)

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN hoặc COLLECTOR

    // Danh sách các hóa đơn mà nhân viên này đã thu
    @OneToMany(mappedBy = "collector")
    private List<Bill> collectedBills;
}