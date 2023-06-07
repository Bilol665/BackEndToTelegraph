package uz.pdp.backendtotelegraph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.backendtotelegraph.entity.TelegraphEntity;
import uz.pdp.backendtotelegraph.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity searchUserEntityByUsernameAndPassword(String username, String password);
    Optional<UserEntity> findUserEntitiesByUsername(String username);
}
