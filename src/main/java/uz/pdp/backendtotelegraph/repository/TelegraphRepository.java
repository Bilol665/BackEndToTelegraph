package uz.pdp.backendtotelegraph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.backendtotelegraph.entity.TelegraphEntity;

import java.util.UUID;

@Repository
public interface TelegraphRepository extends JpaRepository<TelegraphEntity, UUID> {
}
