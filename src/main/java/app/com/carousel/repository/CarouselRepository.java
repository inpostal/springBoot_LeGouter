package app.com.carousel.repository;

import app.com.carousel.vo.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarouselRepository extends JpaRepository<Carousel, Integer> {
}
