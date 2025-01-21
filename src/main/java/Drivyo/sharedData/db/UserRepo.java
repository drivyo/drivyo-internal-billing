package Drivyo.sharedData.db;

import Drivyo.sharedData.dto.enums.UserStatus;
import Drivyo.sharedData.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    @Query(value = """
                SELECT u FROM UserEntity u
                    JOIN FETCH u.role r
                    LEFT JOIN FETCH r.authorities
                    WHERE u.username = :username
            """)
    Optional<UserEntity> findByUsername(@Param("username") String username);

    @Query(value = """
            SELECT u FROM UserEntity u
            WHERE  u.role.id=2
            """)
    List<UserEntity> findManagers();

    boolean existsByIdAndActive(long id, UserStatus status);
}
