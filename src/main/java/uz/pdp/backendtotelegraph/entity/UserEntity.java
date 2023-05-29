package uz.pdp.backendtotelegraph.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserEntity extends BaseEntity {
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
}
