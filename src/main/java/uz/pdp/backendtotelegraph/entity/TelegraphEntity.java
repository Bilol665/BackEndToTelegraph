package uz.pdp.backendtotelegraph.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "telegraphs")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TelegraphEntity extends BaseEntity{
    @Column(nullable = false)
    private String title;
    private String story;
    private String link;
    @ManyToOne
    private UserEntity author;
}
