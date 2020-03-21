package model;

public enum OrderStatus {
    REQUEST("주문 요청"),
    COMPLETE("주문 완료"),
    CANCEL("주문 취소");

    private String name;

    OrderStatus(String name){
        this.name = name;
    }
}
