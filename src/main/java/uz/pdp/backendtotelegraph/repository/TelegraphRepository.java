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
    List<TelegraphEntity> findTelegraphEntitiesByTitleOrderByTitleAsc(String title);
    List<TelegraphEntity> findTelegraphEntitiesByTitleOrderByTitleDesc(String title);
    List<TelegraphEntity> findTelegraphEntitiesByCreatedDateOrderByCreatedDateAsc(LocalDateTime createdDate);
    List<TelegraphEntity> findTelegraphEntitiesByCreatedDateOrderByCreatedDateDesc(LocalDateTime createdDate);
}
