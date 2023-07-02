package app.com.dessertWishlist.vo;

import lombok.Data;

@Data
public class DessertWishlistDTO {

    private Integer dessertId;
    private Integer dessertPrice;
    private String dessertName;
    private byte[] dessertImg;
    private Integer memberId;
}


