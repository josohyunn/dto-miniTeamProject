package com.example.kakao.cart;

import java.util.List;
import java.util.stream.Collectors;

import com.example.kakao.product.Product;
import com.example.kakao.product.option.Option;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class CartResponse {

    // (기능3) 장바구니 조회
    @ToString
    @Getter
    @Setter
    public static class FindAllByUserDTO {
        private Integer totalPrice;
        private List<ProductDTO> products;

        public FindAllByUserDTO(List<Cart> cartList) {
            // 스트림은 물길에 펼쳐지면 타입이 없다.
            this.totalPrice = cartList.stream()
                    .mapToInt(cart -> cart.getPrice()) // int 타입으로 가공하겠다.
                    .sum();
            this.products = cartList.stream()
                    .map(cart -> cart.getOption().getProduct()).distinct()
                    .map(product -> new ProductDTO(product, cartList)) // (p1, cartList) / (p2, cartList)로 한 product에
                                                                       // cartList를 3개 다 던지고 거기서 걸러낸다.
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        class ProductDTO {
            private String productName;
            // List<OptionDTO>로 만들어도 되는데 실무에서는 너무 깊이 들어가기 때문에 필드로 빼놓음
            // 하지만 우리는 이렇게 못한다. Option은 List이기 때문에..
            // private String optionName;
            // private Integer optionQuantity;
            // private Integer optiontotalPrice;
            private List<CartDTO> carts;

            public ProductDTO(Product product, List<Cart> carts) {
                this.productName = product.getProductName();
                // filter은 boolean만 return
                this.carts = carts.stream()
                        .filter(cart -> cart.getOption().getProduct().getId() == product.getId())
                        .map(cart -> new CartDTO(cart))
                        .collect(Collectors.toList());
            }

            @Getter
            @Setter
            class CartDTO {
                private String cartOptionName;
                private Integer cartQuantity;
                private Integer cartPrice;

                public CartDTO(Cart cart) {
                    this.cartOptionName = cart.getOption().getOptionName();
                    this.cartQuantity = cart.getQuantity();
                    this.cartPrice = cart.getPrice();
                }

            }
        }

    }
}
