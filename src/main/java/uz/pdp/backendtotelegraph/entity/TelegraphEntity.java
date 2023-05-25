package uz.pdp.backendtotelegraph.entity;


import jakarta.persistence.Entity;
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
    private String title;
    private String story;
    private String username;
    private String link;
}
