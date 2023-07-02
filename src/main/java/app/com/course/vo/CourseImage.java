package app.com.course.vo;

import javax.persistence.*;


@Entity
@Table(name = "COURSE_IMG")
public class CourseImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_IMG_ID")
    private int courseImgId;

    @Lob
    @Column(name = "COURSE_IMG")
    private byte[] courseImg;

    @Column(name = "COURSE_ID")
    private int courseId;

    @Column(name = "COURSE_IMG_NAME")
    private String courseImgName;
    // Getter and Setter methods

    public String getCourseImgName() {
        return courseImgName;
    }

    public void setCourseImgName(String courseImgName) {
        this.courseImgName = courseImgName;
    }

    public int getCourseImgId() {
        return courseImgId;
    }

    public void setCourseImageId(int courseImgId) {
        this.courseImgId = courseImgId;
    }

    public byte[] getCourseImage() {
        return courseImg;
    }

    public void setCourseImage(byte[] courseImg) {
        this.courseImg = courseImg;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    public Integer getCourseVideoId() {
        return courseImgId;
    }
}