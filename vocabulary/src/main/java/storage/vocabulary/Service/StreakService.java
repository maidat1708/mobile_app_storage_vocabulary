package storage.vocabulary.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import storage.vocabulary.Model.Streak;
import storage.vocabulary.Repository.StreakRepository;

@Service
public class StreakService {
    @Autowired
    private StreakRepository repo;

    public Streak getStreak(){
        return repo.findAll().get(0);
    }
     
    public Streak updateStreak(Streak streak){
        return repo.save(streak);
    }
}
