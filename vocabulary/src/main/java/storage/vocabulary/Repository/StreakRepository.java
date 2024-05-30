package storage.vocabulary.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import storage.vocabulary.Model.Streak;

@Repository
public interface StreakRepository extends JpaRepository<Streak,Integer>{
}
