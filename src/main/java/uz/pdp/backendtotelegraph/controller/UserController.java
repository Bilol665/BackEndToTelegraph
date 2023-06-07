package uz.pdp.backendtotelegraph.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.backendtotelegraph.entity.UserEntity;
import uz.pdp.backendtotelegraph.entity.dto.LoginDto;
import uz.pdp.backendtotelegraph.entity.dto.UserCreateDto;
import uz.pdp.backendtotelegraph.exceptions.TelegraphInvalidException;
import uz.pdp.backendtotelegraph.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class UserController {
    private final UserService userService;
    @PostMapping(value = "/sign-up")
    public ResponseEntity<Object> signUp(
            @Valid @RequestBody UserCreateDto userCreateDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            throw new TelegraphInvalidException("User with this username is already exists!");
        }
        UserEntity add = userService.add(userCreateDto);
        return new ResponseEntity<>(add, HttpStatus.OK);
    }
    @GetMapping(value = "/login")
    public ResponseEntity<Object> signIn(
            @RequestBody LoginDto login
    ) {
        return new ResponseEntity<>(userService.login(login),HttpStatus.OK);
    }
}
