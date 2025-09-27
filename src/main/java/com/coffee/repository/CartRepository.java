package com.coffee.repository;

import com.coffee.entity.Cart;
import com.coffee.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    //특정 회원이 'Cart' 를 가지고 있는지 확인합니다. (만약에 없으면 신규 'Cart' 를 생성.)
    Optional<Cart> findByMember(Member member);
}
