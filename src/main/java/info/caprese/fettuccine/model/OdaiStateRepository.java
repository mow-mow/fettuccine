package info.caprese.fettuccine.model;

import info.caprese.fettuccine.entity.Odai;
import info.caprese.fettuccine.entity.OdaiState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OdaiStateRepository extends JpaRepository<OdaiState, LocalDateTime> {
}
