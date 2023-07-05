package app.com.dessertWishlist.service;

import app.com.dessert5.dao.Dessert5CartRepository;
import app.com.dessert5.dao.Dessert5LoveListRepository;
import app.com.dessert5.dao.DessertImageRepository;
import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.vo.Dessert;
import app.com.dessert5.vo.Dessert5Cart;
import app.com.dessert5.vo.Dessert5CartPK;
import app.com.dessertWishlist.repository.DessertWishlistRepository;
import app.com.dessertWishlist.vo.DessertWishlist;
import app.com.dessertWishlist.vo.DessertWishlistDTO;
import app.com.dessertWishlist.vo.DessertWishlistId;
import app.com.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DessertWishlistService {

    @Autowired
    DessertWishlistRepository dessertWishlistRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DessertImageRepository dessertImageRepository;

    @Autowired
    DessertRepository dessertRepository;

    @Autowired
    Dessert5CartRepository dessert5CartRepository;

    @Autowired
    Dessert5LoveListRepository dessert5LoveListRepository;

    public void addWishlist(Integer memberId, Integer dessertId) {
        DessertWishlistId id = new DessertWishlistId();
        id.setMemId(memberId);
        id.setDessertId(dessertId);

        if (!dessertWishlistRepository.existsById(id)) {
            DessertWishlist newItem = new DessertWishlist();
            newItem.setMemId(memberId);
            newItem.setDessertId(dessertId);

            dessertWishlistRepository.save(newItem);
        } else {
            throw new RuntimeException("購物清單有一樣的商品了");
        }
    }

    public void delete(Integer dessertId, Integer memId) {
        System.out.println("memId = " + memId);
        System.out.println("dessertId = " + dessertId);
        dessertWishlistRepository.deleteByDessertIdAndMemId(dessertId, memId);
    }


    public List<DessertWishlistDTO> getWishlist(Integer memberId) {

        List<DessertWishlist> dessertWishlists = dessertWishlistRepository.findAllByMemId(memberId);

        List<DessertWishlistDTO> dtoList = new ArrayList<>();
        for (DessertWishlist dessertWishlist : dessertWishlists) {
            DessertWishlistDTO dto = new DessertWishlistDTO();

//            dto.setMemberId(memberId);
//            dto.setDessertId(dessertWishlist.getDessertId());

            Dessert dessert = dessertRepository.getReferenceById(dessertWishlist.getDessertId());
            dto.setDessertName(dessert.getDessertName());
            dto.setDessertPrice(dessert.getDessertPrice());
            dto.setDessertId(dessert.getDessertId());

//            DessertImage dessertImage = dessertImageRepository.getReferenceById(dessertWishlist.getDessertId());

            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<Dessert> getAllByMemberId(Integer memberId) {
        List<DessertWishlist> dessertWishlists = dessertWishlistRepository.findAllByMemId(memberId);

        List<Dessert> desserts = new ArrayList<>();
        for (DessertWishlist dessertWishlist : dessertWishlists) {
            Dessert dessert = dessertRepository.getReferenceById(dessertWishlist.getDessertId());
            desserts.add(dessert);
        }
        return desserts;
    }

    public void addToCart(Integer dessertId, Integer memberId) {
        Dessert5CartPK dessert5CartPK = new Dessert5CartPK(dessertId, memberId);
        Dessert5Cart dessert5Cart = dessert5CartRepository.findById(dessert5CartPK).orElse(null);

        if (dessert5Cart == null) {
            Dessert5Cart newCart = new Dessert5Cart();
            newCart.setId(dessert5CartPK);
            newCart.setQuantity(1);
            dessert5CartRepository.save(newCart);
        } else {
            Integer p = dessert5Cart.getQuantity();
            dessert5Cart.setQuantity(p + 1);
            dessert5CartRepository.save(dessert5Cart);
        }
    }
}
