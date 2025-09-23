package com.coffee.entity;

import com.coffee.constant.Category;
import jakarta.persistence.*; //jakarta 의 모든 값을 import 함.
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

//상품 1개에 대한 정보를 저장하고 있는 자바 클래스
@Getter @Setter @ToString
@Entity
@Table(name = "products")
public class Product {
    //엔터티 코딩 작성시 database 의 제약 조건도 같이 고려해야 합니다.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false) //값 입력 필수
    private String name;

    @Column(nullable = false)
    @Min(value = 100, message = "가격은 100원 이상이어야 합니다.") //cf) @Max
    private int price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Min(value = 10, message = "재고 수량은 10개 이상이어야 합니다.")
    @Max(value = 3000, message = "재고 수량은 3000개 이하이어야 합니다.")
    private int stock;

    @Column(nullable = false)
    @NotBlank(message = "이미지는 필수 입력 사항입니다.")
    private String image;

    @Column(nullable = false, length = 1000)
    @NotBlank(message = "상품 설명은 필수 입력 사항입니다.")
    @Size(max = 1000, message = "상품에 대한 설명은 최대 1,000자리 이하로만 입력해 주세요.")
    private String description;

    private LocalDate inputdate; //입고 일자
}
