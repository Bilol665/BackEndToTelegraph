package uz.pdp.backendtotelegraph.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;
import uz.pdp.backendtotelegraph.entity.UserEntity;
import uz.pdp.backendtotelegraph.entity.dto.TelegraphDto;
import uz.pdp.backendtotelegraph.entity.dto.UserCreateDto;
import uz.pdp.backendtotelegraph.exceptions.AuthenticationException;
import uz.pdp.backendtotelegraph.exceptions.DataNotException;
import uz.pdp.backendtotelegraph.exceptions.UserCreationException;
import uz.pdp.backendtotelegraph.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public UserEntity add(UserCreateDto userCreateDto) {
        if(userCreateDto.getEmail().isBlank() || userCreateDto.getUsername().isBlank() || userCreateDto.getPassword().isBlank()) {
            throw new UserCreationException("User properties are blank!");
        }
        try {
            UserEntity map = modelMapper.map(userCreateDto, UserEntity.class);
            return userRepository.save(map);
        } catch (Exception e) {
            throw new DataNotException("User with this username is already exists!");
        }
    }

    public UserEntity signIn(String username, String password) {
        if(username.isBlank() || password.isBlank()) {
            throw new AuthenticationException("username or password is empty");
        }
        return userRepository.searchUserEntityByUsernameAndPassword(username, password);
    }
}
