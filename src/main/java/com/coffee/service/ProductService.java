package com.coffee.service;

import com.coffee.entity.Product;
import com.coffee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //상품에 대한 여러가지 로직 정보를 처리해주는 서비스 클래스입니다.
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductList() {
        return this.productRepository.findProductByOrderByIdDesc();
    }

    public boolean deleteProduct(Long id) {
        //existsById 메소드와 deleteById() 메소드는 'CrudRepository' 에 포함되어 있습니다.
        if(productRepository.existsById(id)){ //해당 항목이 존재하면
            this.productRepository.deleteById(id); //삭제하기
            return true; //true 의 의미는 "삭제 성공" 했습니다.

        }else{ //존재하지 않으면
            return false;
        }
    }

    public void save(Product product) {
        //save() 메소드는 'CrudRepository' 에 포함되어 있습니다.
        this.productRepository.save(product);
    }

    public Product getProductById(Long id) {
        //findById() 메소드는 CrudRepository 에 포함되어 있습니다.
        //그리고 Optional<>을 반환합니다.
        //Optional : 해당 상품이 있을수도 있지만, 경우에 따라서 없을수도 있습니다.
        Optional<Product> product = this.productRepository.findById(id);

        return product.orElse(null);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}