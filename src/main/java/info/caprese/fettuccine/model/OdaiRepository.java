package info.caprese.fettuccine.model;

import info.caprese.fettuccine.entity.Odai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OdaiRepository extends JpaRepository<Odai, String> {
    List<Odai> findByTargetDate(String targetDate);
}
