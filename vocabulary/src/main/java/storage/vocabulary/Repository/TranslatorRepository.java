package storage.vocabulary.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import storage.vocabulary.Model.Translator;

@Repository
public interface TranslatorRepository extends JpaRepository<Translator,Integer>{
    @Query( value ="SELECT * FROM translator order by id DESC limit 10", nativeQuery = true )
    List<Translator> findLast10();
    @Query( value ="SELECT * FROM translator order by id DESC limit 20", nativeQuery = true )
    List<Translator> findLast20();
}
