package storage.vocabulary.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import storage.vocabulary.Model.Streak;
import storage.vocabulary.Service.StreakService;

@CrossOrigin
@RestController
public class StreakController {
    @Autowired
    private StreakService service;

    @GetMapping("/getstreak")
    public Streak getStreak(){
        return service.getStreak();
    }

    @PutMapping("/updatestreak")
    public Streak updateStreak(@RequestBody Streak streak){
        return service.updateStreak(streak);
    }

}
