package ua.company.taxi.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.company.taxi.model.entity.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Collections;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client implements UserDetails {

    private Long id;
    @Pattern(regexp = "[A-Za-z0-9]{3,15}")
    @NotEmpty
    private String login;
    @Pattern(regexp = "[A-Za-z0-9]{6,15}")
    @NotEmpty
    private String password;

    private Long totalSpentValue;

    private Role role;

    private Long numRides;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getRole());
    }

    @Override
    public String getUsername() {
     return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
