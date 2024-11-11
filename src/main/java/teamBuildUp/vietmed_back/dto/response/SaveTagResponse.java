package teamBuildUp.vietmed_back.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveTagResponse {

    private Long tagId;

    @Builder
    public SaveTagResponse(Long tagId) {
        this.tagId = tagId;
    }
}
