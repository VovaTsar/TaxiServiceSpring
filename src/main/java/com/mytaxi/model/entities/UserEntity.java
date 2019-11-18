package com.mytaxi.model.entities;

import com.mytaxi.model.domain.enums.DriverStatus;
import com.mytaxi.model.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long userId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "surname", nullable = false, length = 20)
    private String surname;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true, length = 20)
    private String email;

    @Column(name = "driver_status", nullable = false, length = 20)
    private DriverStatus driverStatus;

    @OneToOne
    @JoinColumn(name = "id_car")
    private CarEntity car;

    @Column(name = "active", nullable = false, length = 20)
    private Boolean active;

    @Column(name = "role", nullable = false, length = 20)
    private Role role;
}