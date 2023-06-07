package uz.pdp.backendtotelegraph.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.backendtotelegraph.entity.TelegraphEntity;
import uz.pdp.backendtotelegraph.entity.UserEntity;
import uz.pdp.backendtotelegraph.entity.dto.TelegraphDto;
import uz.pdp.backendtotelegraph.exceptions.DataNotFoundException;
import uz.pdp.backendtotelegraph.exceptions.PageNotFoundException;
import uz.pdp.backendtotelegraph.exceptions.TelegraphInvalidException;
import uz.pdp.backendtotelegraph.exceptions.TelegraphNotCreatedException;
import uz.pdp.backendtotelegraph.repository.TelegraphRepository;
import uz.pdp.backendtotelegraph.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

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
        UserEntity userEntity;
        try {
            userEntity = userRepository.findById(userId).orElseThrow((Supplier<Throwable>) () -> new DataNotFoundException("User not found"));
        } catch (Throwable e) {
            throw new DataNotFoundException("User not found!");
        }
        map.setAuthor(userEntity);
        map.setLink(userEntity.getUsername() + map.getTitle() + LocalDateTime.now().getSecond());
        try {
            telegraphRepository.save(map);
        } catch (Exception e) {
            throw new TelegraphInvalidException("Telegraph title cannot be blank!");
        }
    }
    public List<TelegraphEntity> getHisAllSorted(UUID userId,int pageSize,int whichPage) {
        Pageable pageable = PageRequest.of(whichPage,pageSize, Sort.by("created_date"));
        return telegraphRepository.findTelegraphEntitiesByAuthorOrderByCreatedDateAsc(userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("Author not found!")
        ),pageable);
    }

    public List<TelegraphEntity> search(String date, String title,boolean sortByTitle,boolean sortByDate) {
        if(date.isBlank() && !title.isBlank()) {
            if(sortByTitle) {
                return telegraphRepository.findTelegraphEntitiesByTitleContainsIgnoreCaseOrderByTitleAsc(title);
            } else {
                return telegraphRepository.findTelegraphEntitiesByTitleContainsIgnoreCaseOrderByTitleDesc(title);
            }
        }else if(title.isBlank() && !date.isBlank()) {
            LocalDateTime localDateTime = LocalDateTime.parse(date + "T00:00:00");
            LocalDateTime localDateTime1 = LocalDateTime.of(localDateTime.getYear(),localDateTime.getMonth(),localDateTime.getDayOfMonth()+1,localDateTime.getHour(),localDateTime.getMinute());
            if(sortByDate) {
                return telegraphRepository.findTelegraphEntitiesByCreatedDateBetweenOrderByCreatedDateAsc(localDateTime,localDateTime1);
            } else {
                return telegraphRepository.findTelegraphEntitiesByCreatedDateBetweenOrderByCreatedDateDesc(localDateTime,localDateTime1);
            }
        }
        throw new TelegraphInvalidException("Date is invalid!");
    }
    public TelegraphEntity getByLink(String link) {
        try {
            return telegraphRepository.findTelegraphEntityByLinkContainsIgnoreCaseOrderByLinkAsc(link);
        } catch (Exception e) {
            throw new DataNotFoundException("Link not found!");
        }
    }
}
