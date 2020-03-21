package service.order;

import java.time.LocalDateTime;

import exception.OrderException;
import model.ItemDto;
import model.OrderDto;
import model.OrderStatus;

public class OrderService {

    //주문 생성
    public OrderDto orderCreate(Long userId, ItemDto itemDto, Integer amount) {
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(amount);
        orderDto.setStatus(OrderStatus.REQUEST);
        orderDto.setUserId(userId);
        orderDto.setItem(itemDto);
        orderDto.setOrderDate(LocalDateTime.now());
        return orderDto;
    }

    //주문 취소
    public OrderDto orderCancel(OrderDto orderDto) {
        orderDto.setStatus(OrderStatus.CANCEL);
        orderDto.setOrderCancelDate(LocalDateTime.now());
        return orderDto;
    }

    //주문 상품 수량 변경
    public OrderDto orderUpdateAmount(OrderDto orderDto, Integer amount) {
        if (orderDto.getStatus() != OrderStatus.REQUEST) {
            throw new OrderException("주문정보 갱신에 실패하였습니다.\n주문상태가 \"주문 요청\"이 아닙니다.", orderDto);
        }

        orderDto.setAmount(amount);
        return orderDto;
    }
}
