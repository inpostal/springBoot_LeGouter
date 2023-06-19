package app.com.news.repository;

import app.com.news.vo.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Integer> {
}


