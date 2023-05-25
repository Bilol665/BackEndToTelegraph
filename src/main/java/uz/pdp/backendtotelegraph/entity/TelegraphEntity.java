package uz.pdp.backendtotelegraph.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TelegraphEntity extends BaseEntity{
    private String title;
    private String story;
    private String username;
}
