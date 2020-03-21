package service.order;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import exception.OrderException;
import model.ItemDto;
import model.OrderDto;
import model.OrderStatus;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("주문 기능 테스트")
class OrderServiceTest {

    private OrderDto orderDto;
    private ItemDto itemDto;
    private OrderService orderService;

    //@AfterAll
    @BeforeAll
    static void init() {
        System.out.println("테스트 시작전 한번만 실행");
    }

    //각 테스트 수행전에 수행.
    //@AfterEach
    @BeforeEach
    void setUp() {
        System.out.println("setUp");

        itemDto = new ItemDto();
        itemDto.setId(1L);
        itemDto.setName("테스트 상품");
        itemDto.setPrice(5000);

        orderDto = new OrderDto();
        orderService = new OrderService();
    }

    @Test
    @DisplayName("주문생성 테스트")
    void orderCreate() {
        Long userId = 1000L;
        Integer amount = 2;
        OrderDto orderDto = orderService.orderCreate(userId, itemDto, amount);

        assertAll("check order",
                () -> assertEquals(userId, orderDto.getUserId()),
                () -> assertEquals(amount, orderDto.getAmount()),
                () -> {
                    assertAll("check status and date",
                            () -> assertEquals(OrderStatus.REQUEST, orderDto.getStatus()),
                            () -> assertNotNull(orderDto.getOrderDate())
                    );
                });
    }

    @Test
    @DisplayName("😱 주문취소 테스트")
    void orderCancel() {
        orderDto.setStatus(OrderStatus.COMPLETE);
        OrderDto cancelOrderDto = orderService.orderCancel(orderDto);

        assertEquals(OrderStatus.CANCEL, cancelOrderDto.getStatus());
    }

    @Test
    @DisplayName("╯°□°）╯ 완료된 주문 수량을 변경했을때 throw 체크 테스트")
    void orderUpdateAmount() {
        orderDto.setStatus(OrderStatus.COMPLETE);
        Exception exception = assertThrows(OrderException.class, () -> orderService.orderUpdateAmount(orderDto, 3));

        String expectedMessage = String.format("%s,\norderDto = {%s}", "주문정보 갱신에 실패하였습니다.\n주문상태가 \"주문 요청\"이 아닙니다.", orderDto.toString());
        assertEquals(expectedMessage, exception.getMessage());
    }
}