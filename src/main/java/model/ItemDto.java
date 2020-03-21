package model;

import lombok.Data;

@Data
public class ItemDto {
    //상품 아이디
    private Long id;

    //상품명
    private String name;

    //상품가격
    private Integer price;

}
