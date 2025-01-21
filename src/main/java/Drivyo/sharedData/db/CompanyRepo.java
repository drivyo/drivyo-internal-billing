package Drivyo.sharedData.db;

import Drivyo.sharedData.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<CompanyEntity, Long> {

    @Query(value = """
                SELECT c FROM CompanyEntity c
                    WHERE c.cif = :cif
            """)
    Optional<CompanyEntity> findByCif(@Param("cif") String cif);

    @Query(value = """
                SELECT c FROM CompanyEntity c
                    WHERE c.name = :name
            """)
    Optional<CompanyEntity> findByName(@Param("name") String name);

    @Query(value = """
                SELECT c FROM CompanyEntity c
                    WHERE c.name = :name and c.id != :id ORDER BY :id
                    LIMIT 1
            """)
    Optional<CompanyEntity> findByNameDifferentThanId(@Param("name") String name, @Param("id") Long id);

    @Query(value = """
                SELECT c FROM CompanyEntity c
                    WHERE c.cif = :cif and c.id != :id ORDER BY :id
                    LIMIT 1
            """)
    Optional<CompanyEntity> findByCifDifferentThanId(@Param("cif") String cif, @Param("id") Long id);
}
