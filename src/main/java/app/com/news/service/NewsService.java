package app.com.news.service;

import app.com.news.repository.NewsRepository;
import app.com.news.vo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    private NewsRepository repository;

    public List<News> getAllNews() {
        List<News> all = repository.findAll();
        return all;
    }

    public News getNewsById(Integer newsId){
        Optional<News>update=repository.findById(newsId);
        return update.get();
    }
    public void add(News news) {
        repository.save(news);
    }

    public void update(News news) {
        repository.save(news);
    }

    public void delete(Integer newsId) {
        repository.deleteById(newsId);
    }
}
