package teamBuildUp.vietmed_back.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveProductResponse {

    private Long productId;

    @Builder
    public SaveProductResponse(Long productId) {
        this.productId = productId;
    }
}
