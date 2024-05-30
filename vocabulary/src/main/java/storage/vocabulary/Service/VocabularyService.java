package storage.vocabulary.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import storage.vocabulary.Model.Statistic;
import storage.vocabulary.Model.Vocabulary;
import storage.vocabulary.Repository.VocabularyRepository;

@Service
public class VocabularyService {
    
    @Autowired
    private VocabularyRepository repo;

    public List<Vocabulary> findAll(){
        return repo.findAll();
    }

    public List<Vocabulary> findByWord(String word){
        return repo.findByWordStartsWith(word);
    }
    
    public Vocabulary findById(int id){
        return repo.getById(id);
    }

    public List<Vocabulary> getRandWord(int limit){
        return repo.getRandWord(limit);
    }

    public List<Vocabulary> getRandAnsFalse(){
        return repo.getRandAnsFalse();
    }
    public List<Statistic> geStatistics(){
        List<Object[]> list = repo.getStatistics();
        List<Statistic> statistics = new ArrayList<>();

        for (Object[] row : list) {
            String wordType = (String) row[0]; // Lấy giá trị từ cột word_type
            Long totalCount = ((Number) row[1]).longValue(); // Lấy giá trị từ cột total_count và ép kiểu về Long

            // Tạo đối tượng Statistic từ giá trị cột
            Statistic statistic = new Statistic();
            statistic.setWord_type(wordType);
            statistic.setTotal_count((int)(long)totalCount);

            statistics.add(statistic); // Thêm đối tượng Statistic vào danh sách
        }
        return statistics;
    }

    public Vocabulary addVocabulary(Vocabulary vocabulary){
        return repo.save(vocabulary);
        // return "add vocabulary success";
    }

    public Vocabulary updateVocabulary(Vocabulary vocabulary){
        return repo.save(vocabulary);
        // return "update vocabulary success";
    }

    public List<Vocabulary> findByMeaning(String meaning){
        return repo.findByMeaning(meaning);
    }

    public void deleteVocabulary(int id){
        repo.deleteById(id);
        // return "delete vocabulary success";
    }
}
