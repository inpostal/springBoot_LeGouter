package app.com.course.main;

import app.com.course.repository.CourseRepository;
import app.com.course.vo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  // 給Spring IoC 託管
public class EvelynMain implements CommandLineRunner {// 這個class會在伺服器部屬完執行(main方法)



    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void run(String... args){
        List<Course> all = courseRepository.findAll();
        for (Course c: all){
//            System.out.println(c);
        }

    }
}
