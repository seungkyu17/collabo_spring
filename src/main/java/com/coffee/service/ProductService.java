package com.coffee.service;

import com.coffee.dto.SearchDto;
import com.coffee.entity.Product;
import com.coffee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    public Optional<Product> findById(Long id) {return productRepository.findById(id);

    }

    public Optional<Product> findProductById(Long productId) {
        return this.productRepository.findById(productId);
    }

    public List<Product> getProductByFilter(String filter) {
        if(filter != null && !filter.isEmpty()){
            return productRepository.findByImageContaining(filter);
        }
        return productRepository.findAll();
    }

    public Page<Product> listProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    //필드 검색 조건과 페이징 기본 정보를 사용하여 상품 목록을 조회하는 로직을 작성합니다.
    public Page<Product> listProducts(SearchDto searchDto, int pageNumber, int pageSize){
        //'Specification' 은 엔터티 객체에 대한 쿼리 조건을 정의할 수 있는 조건자(Specification)로 사용됩니다.
        Specification<Product> spec = Specification.where(null); //'null' 은 현재 어떠한 조건도 없음을 의미합니다.

        //기간 검색 콤보 박스의 조건 추가하기
        if(searchDto.getSearchDateType() != null){
            spec = spec.and(ProductSpecification.hasDateRange(searchDto.getSearchDateType()));
        }

        //카테고리의 조건 추가하기
        if(searchDto.getCategory() != null){
            spec = spec.and(ProductSpecification.hasCategory(searchDto.getCategory()));
        }

        //검색 모드에 따른 조건 추가하기('name' 또는 'description')
        String searchMode = searchDto.getSearchMode();
        String searchKeyword = searchDto.getSearchKeyword();

        if(searchMode != null && searchKeyword != null){
            if("name".equals(searchMode)){ //상품명으로 검색
                spec = spec.and(ProductSpecification.hasNameLike(searchKeyword));
            }else if("description".equals(searchMode)){ //상품 설명으로 검색
                spec = spec.and(ProductSpecification.hasDescriptionLike(searchKeyword));
            }
        }

        //상품의 'id' 를 역순으로 정렬하기
        Sort sort = Sort.by(Sort.Order.desc("id"));

        //'pageNumber' 페이지('0 base')를 보여주되, 'sort 방식' 으로 정렬하여 'pageSize' 개씩 보여준다.
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        return this.productRepository.findAll(spec, pageable);

    }
}