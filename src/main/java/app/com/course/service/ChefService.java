package app.com.course.service;

import app.com.course.repository.ChefRepository;
import app.com.course.vo.Chef;
import app.com.emp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefService {

    @Autowired
    ChefRepository chefRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Chef> getChef() {
        return chefRepository.findAll();
    }

    public Chef getChefById(Integer empId) {
        Optional<Chef> chef = chefRepository.findByEmpId(empId);
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

    public String getEmpNameById(Integer empId) {
        return employeeRepository.findById(empId).get().getEmpName();
    }

    public Chef findByEmpId(Integer empId) {
        return chefRepository.getReferenceByEmpId(empId);
    }
}


