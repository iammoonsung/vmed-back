package teamBuildUp.vietmed_back.dto.response;

import lombok.Builder;
import lombok.Data;
import teamBuildUp.vietmed_back.domain.Product;
import teamBuildUp.vietmed_back.domain.ProductTag;
import teamBuildUp.vietmed_back.domain.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class FindProductResponse {

    private Long productId;
    private List<String> bodyParts;
    private List<String> nutritions;
    private String name;
    private Integer price;
    private String picture;
    private String detail;

    @Builder
    public FindProductResponse(Product product) {
        this.productId = product.getId();
        List<String> bodyParts = new ArrayList<>();
        List<String> nutritions = new ArrayList<>();
        for(ProductTag productTag : product.getProductTags()){
            System.out.println("productTag id == " + productTag.getId() + " tag field == " + productTag.getTag().getTagField() + " tag value == " + productTag.getTag().getTagValue());
            Tag tag = productTag.getTag();
            if ( tag.getTagField().equals("nutrition")) {
                nutritions.add(tag.getTagValue());
            }

            if ( tag.getTagField().equals("bodyPart")) {
                bodyParts.add(tag.getTagValue());
            }
        }
        this.nutritions = nutritions;
        this.bodyParts = bodyParts;
        this.name = product.getName();
        this.picture = product.getPicture();
        this.price = product.getPrice();
    }
}
