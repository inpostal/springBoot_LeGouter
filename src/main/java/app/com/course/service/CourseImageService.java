package app.com.course.service;

import app.com.course.repository.CourseImageRepository;
import app.com.course.repository.CourseRepository;
import app.com.course.vo.CourseImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseImageService {
    @Autowired
CourseImageRepository courseImageRepository;
public void getCourseImageById(Integer courseId) {
    Optional<CourseImage> courseImage = courseImageRepository.findById(courseId);
    }


}
