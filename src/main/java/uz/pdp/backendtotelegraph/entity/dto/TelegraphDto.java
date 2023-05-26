package uz.pdp.backendtotelegraph.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TelegraphDto {
    private String title;
    private String story;
    private String username;
    private String link;
}
