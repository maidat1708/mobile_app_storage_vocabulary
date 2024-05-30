package storage.vocabulary.Answer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer>{
    List<Answer> getAnswerByIdListen(int id);
    
}
