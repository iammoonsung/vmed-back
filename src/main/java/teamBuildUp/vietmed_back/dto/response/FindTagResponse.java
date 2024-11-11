package teamBuildUp.vietmed_back.dto.response;

import lombok.Builder;
import lombok.Data;
import teamBuildUp.vietmed_back.domain.Tag;

@Data
public class FindTagResponse {

    private Long tagId;
    private String tagField;
    private String tagValue;

    @Builder
    public FindTagResponse(Tag tag) {
        this.tagId = tag.getId();
        this.tagField = tag.getTagField();
        this.tagValue = tag.getTagValue();
    }
}
