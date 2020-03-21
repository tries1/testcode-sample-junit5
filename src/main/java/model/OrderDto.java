package model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderDto {
    //주문 아이디
    private Long id;

    //주문 수량
    private Integer amount;

    //주문 상태
    private OrderStatus status;

    //주문자 고객 ID
    private Long userId;

    //주문 상품 목록
    private ItemDto item;

    //주문일
    private LocalDateTime orderDate;

    //취소일
    private LocalDateTime orderCancelDate;
}
