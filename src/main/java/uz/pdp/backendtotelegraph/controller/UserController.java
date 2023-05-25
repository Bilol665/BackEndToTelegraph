package uz.pdp.backendtotelegraph.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.backendtotelegraph.entity.UserEntity;
import uz.pdp.backendtotelegraph.entity.dto.UserCreateDto;
import uz.pdp.backendtotelegraph.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping(value = "/sign-up")
    public ResponseEntity<Object> signUp(
            @RequestBody UserCreateDto userCreateDto
    ) {
        userService.add(userCreateDto);
        return new ResponseEntity<>("You are signed up", HttpStatus.OK);
    }
}
