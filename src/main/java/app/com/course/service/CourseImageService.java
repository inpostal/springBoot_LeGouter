package app.com.course.service;

import app.com.course.repository.CourseImageRepository;
import app.com.course.vo.CourseImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseImageService {
    @Autowired
    CourseImageRepository courseImageRepository;

    //getReferenceById全部取出 findBy的話是延遲載入
    public CourseImage getCourseImageById(Integer pk) {
        CourseImage courseImage = courseImageRepository.getReferenceById(pk);
        return courseImage;
    }


    public void add(CourseImage video) {
        courseImageRepository.save(video);
    }

    public List<CourseImage> getCourseVideoById(Integer courseId) {
        List<CourseImage> list = courseImageRepository.findAllByCourseId(courseId);
        return list;
    }
}
