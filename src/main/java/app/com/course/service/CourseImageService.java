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

    public List<CourseImage> getCourseImagesByIds(List<Integer> courseVideoIds) {
        List<CourseImage> list = courseImageRepository.findAllById(courseVideoIds);
        return list;
    }

    public void deleteAll(List<CourseImage> existingImages) {
        courseImageRepository.deleteAll(existingImages);
    }

    public CourseImage getVideoById(Integer courseImgId) {
        return courseImageRepository.findById(courseImgId).get();
    }

    public void delete(CourseImage video) {
        courseImageRepository.delete(video);
    }
}
