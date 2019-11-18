package com.mytaxi.model.domain;

import com.mytaxi.model.domain.enums.DriverStatus;
import com.mytaxi.model.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Data
@Builder
@AllArgsConstructor
public class User {
    private Long userId;

    @Pattern(regexp = "[a-zA-Z\\p{IsCyrillic}]{3,20}")
    @NotEmpty
    private String name;

    @Pattern(regexp = "(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}")
    @NotEmpty
    private String surname;

    @Pattern(regexp = "[a-zA-Z0-9]{5,20}")
    @NotEmpty
    private String password;

    @Pattern(regexp = "^(\\+380)([0-9]{9})")
    @NotEmpty
    private String phoneNumber;

    @Pattern(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")
    @NotEmpty
    private String email;

    @NotNull
    private DriverStatus driverStatus;

    @NotNull
    private Car car;

    @NotNull
    private Boolean active;

    @NotNull
    private Role role;

    public User(User user, String encoded) {

    }
}
