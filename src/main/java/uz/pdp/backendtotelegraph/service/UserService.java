package uz.pdp.backendtotelegraph.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;
import uz.pdp.backendtotelegraph.entity.UserEntity;
import uz.pdp.backendtotelegraph.entity.dto.UserCreateDto;
import uz.pdp.backendtotelegraph.exceptions.UserCreationException;
import uz.pdp.backendtotelegraph.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public UserEntity add(UserCreateDto userCreateDto) {
        if(userCreateDto.getEmail().isBlank() || userCreateDto.getUsername().isBlank() || userCreateDto.getPassword().isBlank()) {
            throw new UserCreationException("User properties are blank!");
        }
        UserEntity map = modelMapper.map(userCreateDto, UserEntity.class);
        return userRepository.save(map);
    }
}
