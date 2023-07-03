package app.com.dessertWishlist.service;

import app.com.dessert5.dao.DessertImageRepository;
import app.com.dessert5.dao.DessertRepository;
import app.com.dessert5.vo.Dessert;
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

    public void removeWishlist(Integer memberId, Integer dessertId) {
        DessertWishlistId id = new DessertWishlistId();
        id.setMemId(memberId);
        id.setDessertId(dessertId);

        if (dessertWishlistRepository.existsById(id)) {
            dessertWishlistRepository.deleteById(id);
        } else {
            throw new RuntimeException("The wishlist item does not exist.");
        }
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
}
