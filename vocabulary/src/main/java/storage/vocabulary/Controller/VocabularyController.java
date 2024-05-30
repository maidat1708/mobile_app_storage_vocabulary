package storage.vocabulary.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import storage.vocabulary.Model.Statistic;
import storage.vocabulary.Model.Vocabulary;
import storage.vocabulary.Service.VocabularyService;

@CrossOrigin
@RestController
public class VocabularyController {

    @Autowired
    private VocabularyService service;
    
    // @ExceptionHandler(MissingServletRequestParameterException.class)
    // public List<Vocabulary> missingParam(MissingServletRequestParameterException e){
    //     return service.findAll();
    // }
    @GetMapping("/getbyword")
    public List<Vocabulary> getByWord(@RequestParam("word") String word){
        if(word == null){
            return service.findAll();
        }
        return service.findByWord(word);
    }

    @GetMapping("/getbymeaning/{meaning}")
    public List<Vocabulary> getByMeaning(@PathVariable String meaning){
        return service.findByMeaning(meaning);
    }

    @GetMapping("/getbyid/{id}")
    public Vocabulary findById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping("/practicelearnword/{number}")
    public List<Vocabulary> getRandWord(@PathVariable int number){
        return service.getRandWord(number);
    }
    @GetMapping("/statistic")
    public List<Statistic> geStatistics(){
        return service.geStatistics();
    }
    @GetMapping("/selectanswer")
    public List<Vocabulary> getAnsFalse(){
        Vocabulary vocabulary = service.getRandWord(1).get(0);
        List<Vocabulary> list = service.getRandAnsFalse();
        while(list.contains(vocabulary)){
            list = service.getRandAnsFalse();
        }
        list.add(vocabulary);
        return list;
    }

    @PostMapping("/addvocabulary")
    public Vocabulary addVocabulary(@RequestBody Vocabulary vocabulary){
        return service.addVocabulary(vocabulary);
    }

    @PutMapping("/updatevocabulary")
    public Vocabulary updateVocabulary(@RequestBody Vocabulary vocabulary){
        System.out.println(vocabulary.getImage());
        return service.updateVocabulary(vocabulary);
    }

    @DeleteMapping("/deletevocabulary/{id}")
    public void deleteVocabulary(@PathVariable int id){
        service.deleteVocabulary(id);
    }
}
