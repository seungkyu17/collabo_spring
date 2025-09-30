package com.coffee.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderListController {

//    @Autowired
//    private OrderService orderService;
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<List<OrderDto>> getOrderList(@PathVariable Long userId){
//        List<OrderDto> orders = orderService.getOrdersByUserId(userId);
//        return ResponseEntity.ok(orders);
//    }
}
