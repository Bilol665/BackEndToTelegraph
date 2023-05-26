package uz.pdp.backendtotelegraph.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.backendtotelegraph.entity.TelegraphEntity;
import uz.pdp.backendtotelegraph.entity.UserEntity;
import uz.pdp.backendtotelegraph.entity.dto.TelegraphDto;
import uz.pdp.backendtotelegraph.exceptions.DataNotException;
import uz.pdp.backendtotelegraph.exceptions.PageNotFoundException;
import uz.pdp.backendtotelegraph.exceptions.TelegraphInvalidException;
import uz.pdp.backendtotelegraph.exceptions.TelegraphNotCreatedException;
import uz.pdp.backendtotelegraph.repository.TelegraphRepository;
import uz.pdp.backendtotelegraph.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TelegraphService {
    private final TelegraphRepository telegraphRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public void add(TelegraphDto telegraphDto, UUID userId) {
        if(userId == null || telegraphDto.getTitle().isBlank() || telegraphDto.getStory().isBlank()) {
            throw new TelegraphNotCreatedException("Telegraph properties are blank");
        }
        TelegraphEntity map = modelMapper.map(telegraphDto, TelegraphEntity.class);
        UserEntity userEntity = userRepository.findById(userId).get();
        map.setAuthor(userEntity);
        map.setLink(userEntity.getUsername() + map.getTitle() + LocalDate.now());
        try {
            telegraphRepository.save(map);
        } catch (Exception e) {
            throw new TelegraphInvalidException("Telegraph title cannot be blank!");
        }
    }
    public List<TelegraphEntity> getHisAllSorted(UUID userId,int pageSize,int whichPage) {
        if(userId == null) {
            throw new DataNotException("user id not found");
        }
        List<TelegraphEntity> res = new ArrayList<>();
        List<TelegraphEntity> telegraphEntitiesByAuthor = telegraphRepository.findTelegraphEntitiesByAuthor(userRepository.findById(userId).get());
        int size = telegraphEntitiesByAuthor.size();
        if(size <= pageSize) {
            if(whichPage != 1) {
                throw new PageNotFoundException("Page not found");
            }
            return telegraphEntitiesByAuthor;
        }
        size/=pageSize;
        if(size < whichPage) throw new PageNotFoundException("Page not found");
        for (int i = whichPage*pageSize-pageSize; i < whichPage*pageSize; i++) {
            res.add(telegraphEntitiesByAuthor.get(i));
        }
        return res;
    }

    public List<TelegraphEntity> search(String date, String title) {
        if(date.isBlank()) {
            return telegraphRepository.findTelegraphEntitiesByTitleOrderByTitleAsc(title);
        }else if(title.isBlank()) {
//            return telegraphRepository;
        }
        return null;
    }
}
