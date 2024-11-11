package teamBuildUp.vietmed_back.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamBuildUp.vietmed_back.domain.Product;
import teamBuildUp.vietmed_back.domain.ProductTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class SaveProductRequest {

    private String name;
    private Integer price;
    private String picture;
    private String detail;

    private List<String> bodyParts;
    private List<String> nutritions;

    @Builder
    public SaveProductRequest(String name, Integer price, String picture, String detail, List<String> bodyParts, List<String> nutritions) {
        this.name = name;
        this.price = price;
        this.picture = picture;
        this.detail = detail;
        this.bodyParts = bodyParts;
        this.nutritions = nutritions;
    }

    public Product makeProductEntity() {
        List<ProductTag> productTags = new ArrayList<>();

        return Product.createProduct(
                this.name,
                this.picture,
                this.price,
                this.detail
        );
    }
}
