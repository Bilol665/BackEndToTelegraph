package uz.pdp.backendtotelegraph.entity.dto;

import lombok.*;
import org.springframework.lang.NonNull;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserCreateDto {
    private String username;
    private String password;
    private String email;
    private List<TelegraphDto> telegraphs;
}
