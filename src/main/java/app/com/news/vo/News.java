package app.com.news.vo;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEWS_ID")
    private Integer newsId;

    @Column(name = "EMP_ID")
    private Integer empId;

    @Column(name = "NEWS_CONTENT", length = 5000)
    private String newsContent;

    @Lob
    @Column(name = "NEWS_PIC")
    private byte[] newsPic;

    @Column(name = "NEWS_TIME" ,insertable = false)
    private Date newsTime;

    @Column(name = "NEWS_TITLE", length = 255)
    private String newsTitle;
}
