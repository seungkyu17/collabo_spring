package com.coffee.entity;

import com.coffee.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @ToString
@Entity
@Table(name = "orders") //주의) 'order' 는 데이터베이스 전용 키워드 입니다. --> 반드시 복수 표현.
public class Order { //주문과 관련된 'Entity' 입니다.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    //고객 1명이 여러개의 주문을 할수 있습니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    //통상적으로 우리가 주문을 할때 여러개의 '주문 상품'을 동시에 주문합니다.
    //하나의 주문에는 '주문 상품'을 여러개 담을수 있습니다.
    //주의) 'mappedBy' 항목의 'order' 는 'OrderProduct'에 들어있는 'Order 타입'의 변수명입니다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts;

    private LocalDate orderdate; //주문 날짜

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태
}
