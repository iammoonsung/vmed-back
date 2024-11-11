package teamBuildUp.vietmed_back.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class FindTagRequest {

    private Long tagId;
    private String tagField;
    private String tagValue;

    @Builder
    public FindTagRequest(Long tagId, String tagField, String tagValue) {
        this.tagId = tagId;
        this.tagField = tagField;
        this.tagValue = tagValue;
    }

}
