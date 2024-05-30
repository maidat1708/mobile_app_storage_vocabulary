package storage.vocabulary.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import storage.vocabulary.Model.Vocabulary;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary,Integer>{
    List<Vocabulary> findByWordStartsWith(String word);
    List<Vocabulary> findByMeaning(String meaning);
    @Query( value ="SELECT * FROM vocabulary order by rand() limit ?1", nativeQuery = true )
    List<Vocabulary> getRandWord(int limit);
    @Query( value ="SELECT * FROM vocabulary where id = ?1", nativeQuery = true )
    Vocabulary getById(int id);
    @Query( value ="SELECT * FROM vocabulary order by rand() limit 3", nativeQuery = true )
    List<Vocabulary> getRandAnsFalse();
    @Query(value ="SELECT 'total' AS word_type, COUNT(*) AS total_count FROM vocabulary\n" + //
                "UNION ALL\n" + //
                "SELECT type, COUNT(*) AS total_count FROM vocabulary GROUP BY type;", nativeQuery = true )  
    List<Object[]> getStatistics();
}
