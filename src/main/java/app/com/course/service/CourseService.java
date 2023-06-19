package app.com.course.service;

import app.com.course.repository.CourseRepository;
import app.com.course.vo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//找人不找方法, 都叫Service
@Service
public class CourseService {

    @Autowired
    CourseRepository repository;

    public List<Course> getCourse() {
        return repository.findAll();
    }


    public Course getCourseById(Integer courseId) {
        Optional<Course> course = repository.findById(courseId);
        return course.get();
    }


    public void addCourse(Course course) {
        repository.save(course);
    }

    public void delete(Integer courseId) {
        repository.deleteById(courseId);
    }

    public void update(Course course) {
        repository.save(course);
    }


//    public void delete(Integer courseId) {
//        repository.deleteById(courseId);
//    }
//
//    public void update(Course course) {
//        repository.save(course);
//    }
}
