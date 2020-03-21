package dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
    //주문 아이디
    private Long id;

    //주문 수량
    private Long amount;

    //주문 상태
    private Long status;

    //주문 상품 목록
    private List<ItemDto> itemList;

    //주문일
    private LocalDateTime orderDate;
}
