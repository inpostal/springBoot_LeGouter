package app.com.course.service;

import app.com.course.repository.ChefRepository;
import app.com.course.vo.Chef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefService {

    @Autowired
    ChefRepository chefRepository;

    public List<Chef> getChef() {
        return chefRepository.findAll();
    }

    public Chef getChefById(Integer chefId) {
        Optional<Chef> chef = chefRepository.findById(chefId);
        return chef.get();
    }

    public void addChef(Chef chef) {
        chefRepository.save(chef);
    }

    public void delete(Integer chefId) {
        chefRepository.deleteById(chefId);
    }

    public void update(Chef chef) {
        chefRepository.save(chef);
    }

}


