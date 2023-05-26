package uz.pdp.backendtotelegraph.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.backendtotelegraph.entity.TelegraphEntity;
import uz.pdp.backendtotelegraph.entity.UserEntity;
import uz.pdp.backendtotelegraph.entity.dto.TelegraphDto;
import uz.pdp.backendtotelegraph.repository.TelegraphRepository;
import uz.pdp.backendtotelegraph.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TelegraphService {
    private final TelegraphRepository telegraphRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public void add(TelegraphDto telegraphDto, UUID userId) {
        TelegraphEntity map = modelMapper.map(telegraphDto, TelegraphEntity.class);
        Optional<UserEntity> user = userRepository.findById(userId);
        UserEntity userEntity = user.orElse(null);
        List<TelegraphEntity> telegraphs = userEntity.getTelegraphs();
        telegraphs.add(map);
        userEntity.setTelegraphs(telegraphs);
        userRepository.save(userEntity);
    }
}
