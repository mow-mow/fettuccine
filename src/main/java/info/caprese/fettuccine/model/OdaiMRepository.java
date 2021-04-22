package info.caprese.fettuccine.model;

import info.caprese.fettuccine.entity.OdaiM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OdaiMRepository extends JpaRepository<OdaiM, Integer> {
    @Query(value = "SELECT * FROM odai_m ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<OdaiM> findOdaiMRandom();
}
