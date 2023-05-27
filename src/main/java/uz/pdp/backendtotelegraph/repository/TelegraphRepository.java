package uz.pdp.backendtotelegraph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.backendtotelegraph.entity.TelegraphEntity;
import uz.pdp.backendtotelegraph.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TelegraphRepository extends JpaRepository<TelegraphEntity, UUID> {
    List<TelegraphEntity> findTelegraphEntitiesByAuthor(UserEntity author);
    List<TelegraphEntity> findTelegraphEntitiesByTitleContainsIgnoreCaseOrderByTitleAsc(String title);
    List<TelegraphEntity> findTelegraphEntitiesByTitleContainsIgnoreCaseOrderByTitleDesc(String title);
    List<TelegraphEntity> findTelegraphEntitiesByCreatedDateBetweenOrderByCreatedDateAsc(LocalDateTime createdDate, LocalDateTime createdDate2);
    List<TelegraphEntity> findTelegraphEntitiesByCreatedDateBetweenOrderByCreatedDateDesc(LocalDateTime createdDate, LocalDateTime createdDate2);
}
