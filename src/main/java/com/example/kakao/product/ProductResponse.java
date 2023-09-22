package com.example.kakao.product;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ProductResponse {

    // (기능1) 상품 목록보기
    @ToString
    @Getter
    @Setter
    public static class FindAllDTO {
        private int productId;
        private String productName;
        private String productImage;
        private int productPrice; // 톡딜가

        public FindAllDTO(Product product) {
            this.productId = product.getId();
            this.productName = product.getProductName();
            this.productImage = product.getImage();
            this.productPrice = product.getPrice();
        }
    }

    // (기능2) 상품 상세보기
    @Getter
    @Setter
    public static class FindByIdDTO {

    }
}