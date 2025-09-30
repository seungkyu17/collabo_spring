package com.coffee.service;

import com.coffee.entity.Order;
import com.coffee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }


//    @Autowired
//    private OrderRepository orderRepository;
//
//    public List<OrderDto> getOrdersByUserId(Long userId) {
//        List<Order> orders = orderRepository.findByUserId(userId);
//        return orders.stream()
//                .map(order = new OrderDto(order))
//                .collect(Collectors.toList());

}
