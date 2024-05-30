package storage.vocabulary.Answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository repo;

    public List<Answer> getAnswerByIdListen(int id){
        return repo.getAnswerByIdListen(id);
    }
}
