package app.com.carousel.service;


import app.com.carousel.repository.CarouselRepository;
import app.com.carousel.vo.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselService {
    @Autowired
    private CarouselRepository repository;

    public void addCarousel(Carousel carousel) {
        repository.save(carousel);
    }

    public List<Carousel> getAllCarousel() {
        return repository.findAll();
    }

    public Carousel getCarousel(Integer carId) {
        return repository.getReferenceById(carId);
    }

    public void updateCarousel(Carousel carousel) {
        repository.save(carousel);
    }
}
