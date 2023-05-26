package uz.pdp.backendtotelegraph.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String email;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private List<TelegraphEntity> telegraphs;
}
