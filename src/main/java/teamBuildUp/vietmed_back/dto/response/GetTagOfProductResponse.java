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
public class GetTagOfProductResponse {

    private Map<String, List<String>> bodyParts;
    private Map<String, List<String>> nutritions;


    @Builder
    public GetTagOfProductResponse(List<ProductTag> productTags) {
        Map<String, List<String>> bodyParts = new HashMap<String, List<String>>();
        Map<String, List<String>> nutritions = new HashMap<String, List<String>>();
        for(ProductTag productTag : productTags){
            System.out.println("productTag id == " + productTag.getId() + "tag id == " + productTag.getTag().getId());
            Tag tag = productTag.getTag();
            if ( tag.getTagField() == "nutrition") {
                nutritions.computeIfAbsent(tag.getTagField(), k -> new ArrayList<>()).add(tag.getTagValue());
            }

            if ( tag.getTagField() == "bodyPart") {
                bodyParts.computeIfAbsent(tag.getTagField(), k -> new ArrayList<>()).add(tag.getTagValue());
            }
        }
        this.nutritions = nutritions;
        this.bodyParts = bodyParts;
    }
}
