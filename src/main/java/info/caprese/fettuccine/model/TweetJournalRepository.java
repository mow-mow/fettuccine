package info.caprese.fettuccine.model;


import info.caprese.fettuccine.entity.TweetJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetJournalRepository extends JpaRepository<TweetJournal, Integer> {
}