package storage.vocabulary.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import storage.vocabulary.Model.Translator;
import storage.vocabulary.Repository.TranslatorRepository;

@Service
public class TranslatorService {
    private static final String API_KEY = "AIzaSyCzQxURMYOOaqPGt2MS83oaqZJRLHkRnzg";
    @Autowired
    private TranslatorRepository repo;

    public Translator translate(Translator translator){
        Translate translate = TranslateOptions.newBuilder().setApiKey(API_KEY).build().getService();
        Translation translation = translate.translate(translator.getText(), Translate.TranslateOption.targetLanguage(translator.getToLang()),Translate.TranslateOption.sourceLanguage(translator.getFromLang()));
        translator.setTranslatedText(translation.getTranslatedText());
        repo.save(translator);
        return translator;
    }

    public List<Translator> getListTranslators10(){
        return repo.findLast10();
    }

    public List<Translator> getListTranslators20(){
        return repo.findLast20();
    }
}
