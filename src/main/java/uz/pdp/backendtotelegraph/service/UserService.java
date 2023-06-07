package uz.pdp.backendtotelegraph.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.backendtotelegraph.entity.UserEntity;
import uz.pdp.backendtotelegraph.entity.UserRole;
import uz.pdp.backendtotelegraph.entity.dto.LoginDto;
import uz.pdp.backendtotelegraph.entity.dto.UserCreateDto;
import uz.pdp.backendtotelegraph.entity.response.JwtResponse;
import uz.pdp.backendtotelegraph.exceptions.AuthenticationException;
import uz.pdp.backendtotelegraph.exceptions.DataNotFoundException;
import uz.pdp.backendtotelegraph.exceptions.UserCreationException;
import uz.pdp.backendtotelegraph.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserEntity add(UserCreateDto userCreateDto) {
        if(userCreateDto.getEmail().isBlank() || userCreateDto.getUsername().isBlank() || userCreateDto.getPassword().isBlank()) {
            throw new UserCreationException("User properties are blank!");
        }
        try {
            UserEntity map = modelMapper.map(userCreateDto, UserEntity.class);
            map.setHasBlocked(false);
            map.setUserRoles(List.of(UserRole.USER));
            map.setPassword(passwordEncoder.encode(map.getPassword()));
            return userRepository.save(map);
        } catch (Exception e) {
            throw new DataNotFoundException("User with this username is already exists!");
        }
    }



    public JwtResponse login(LoginDto login) {
        UserEntity userEntity = userRepository.findUserEntitiesByUsername(login.getUsername()).orElseThrow(
                () -> new DataNotFoundException("User not found!")
        );
        if(passwordEncoder.matches(login.getPassword(), userEntity.getPassword())) {
            String accessToken = jwtService.generateAccessToken(userEntity);
            return JwtResponse.builder().accessToken(accessToken).build();
        }
        throw new DataNotFoundException("User not found!");
    }
}
