package storage.vocabulary.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import storage.vocabulary.Model.Translator;
import storage.vocabulary.Service.TranslatorService;

@CrossOrigin
@RestController
public class TranslatorController {
    @Autowired
    private TranslatorService service;

    @PostMapping("/translate")
    public Translator translate(@RequestBody Translator translate) throws Exception{
        return service.translate(translate);
    }

    @GetMapping("/getlast10")
    public List<Translator> getLast10(){
        return service.getListTranslators10();
    }

    @GetMapping("/getlast20")
    public List<Translator> getLast20(){
        return service.getListTranslators20();
    }
}
