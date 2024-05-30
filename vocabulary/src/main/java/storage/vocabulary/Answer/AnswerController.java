package storage.vocabulary.Answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin
@RestController
public class AnswerController {

    @Autowired
    private AnswerService service;

    @GetMapping("/getanswerbyidlisten/{id}")
    public List<Answer> getAnswerByIdListen(@PathVariable int id){
        return service.getAnswerByIdListen(id);
    }
    
}
