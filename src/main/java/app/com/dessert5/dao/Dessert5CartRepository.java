package app.com.dessert5.dao;

import app.com.dessert5.vo.Dessert5Cart;
import app.com.dessert5.vo.Dessert5CartPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Dessert5CartRepository extends JpaRepository<Dessert5Cart, Dessert5CartPK> {

}
