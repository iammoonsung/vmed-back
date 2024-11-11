package teamBuildUp.vietmed_back.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductTag> productTags = new ArrayList<>();

    private String name;
    private String picture;
    private Integer price;
    private Integer discountedPrice;
    private Integer discountRate;
    private String detail;

    private void setName(String name) {
        this.name = name;
    }
    private void setPicture(String picture) {
        this.picture = picture;
    }
    private void setPrice(Integer price) {
        this.price = price;
    }
    private void setDetail(String detail) {
        this.detail = detail;
    }

    private Product(String name, String picture, Integer price, String detail) {
        this.name = name;
        this.picture = picture;
        this.price = price;
        this.detail = detail;
    }

    public static Product createProduct(String name, String picture, Integer price, String detail) {
        return new Product(name, picture, price, detail);
    }

    public void addProductTag(ProductTag productTag) {
        productTags.add(productTag);
        productTag.setProduct(this);
    }

    public void removeProductTag(ProductTag productTag) {
        productTags.remove(productTag);
    }
}
