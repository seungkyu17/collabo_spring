package com.coffee.controller;


import com.coffee.entity.Ball;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BallController {
    @GetMapping("/ball")
    public Ball test(){
        Ball bean = new Ball();
        bean.setId("football");
        bean.setName("축구공");
        bean.setPrice(15000);
        return bean;
    }

    @GetMapping("/ball/list")
    public List<Ball> test02(){
        List<Ball> ballList = new ArrayList<>();
        ballList.add(new Ball("football","축구공", 15000));
        ballList.add(new Ball("baseball","야구공", 10000));
        ballList.add(new Ball("basketball","농구공", 18000));
        return ballList;
    }
}
