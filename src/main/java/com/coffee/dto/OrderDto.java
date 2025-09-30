package com.coffee.dto;

import com.coffee.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

//사용자가 주문할때 필요한 변수들을 정의해놓은 클래스(주문 1건의 최소 단위)
@Getter @Setter @ToString
public class OrderDto {
//    private Long orderId;
//    private String productName;
//    private int quantity;
    private LocalDate orderDate;
    private Long memberId; //주문자 정보
    private OrderStatus status;
    private List<OrderItemDto> orderItems; //주문 상품 목록
//
//    public OrderDto(Order order){
//        this.orderId = order.getId();
//        this.productName = order.getProduct().getName();
//        this.quantity = order.getQuantity();
//        this.orderDate = order.getOrderDate();
    }

