package com.coffee.repository;

import com.coffee.constant.OrderStatus;
import com.coffee.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //쿼리 메소드를 사용하여 특정 회원의 송장 번호가 큰 것(최신 주문)부터 조회합니다.
    //주문의 상태가 'PENDING' 인것만 조회합니다.
    //cf. 좀 더 복잡한 쿼리를 사용하시려면 '@Query' 또는 'querydsl' 을 사용하세요.
    List<Order> findByMemberIdAndStatusOrderByIdDesc(Long memberId, OrderStatus status);

    //주문번호(id) 기준으로 모든 주문 내역을 역순(내림차순)으로 조회하려면 'JPA 메소드'를 이렇게 작성하시면 됩니다.
    //주문의 상태가 'PENDING' 인것만 조회합니다.
    List<Order> findByStatusOrderByIdDesc(OrderStatus status); //이건 관리자가 사용합니다.

    //특정 주문에 대하여 주문의 상태를 '주문 완료(COMPLETED)'로 변경합니다.
    //'쿼리 메소드' 대신 '@Query 어노테이션' 사용 예시 : 'sql' 대신 'JPQL'
    //주의사항
    //1. '테이블 이름' 대신 'Entity 이름' 을 명시
    //2. 대, 소문자 구분합니다.
    @Modifying //이 쿼리는 'select 구문' 이 아니고, 데이터 변경을 위한 쿼리입니다.
    @Transactional //헷갈릴때는 다음에 쓰기위해 'import' 를 붙여넣기 해놓는다. --> 'import jakarta.transaction.Transactional;'
    @Query("update Order o set o.status = :status where o.id = :orderId")
    int updateOrderStatus(@Param("orderId") Long orderId, @Param("status") OrderStatus status);
}
