package uz.pdp.backendtotelegraph.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserEntity extends BaseEntity implements UserDetails {
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private List<UserRole> userRoles;
    private Boolean hasBlocked;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String ROLE = "ROLE_";
        List<GrantedAuthority> authorities = new ArrayList<>();
        userRoles.forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(ROLE + role.name()));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
