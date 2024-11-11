package teamBuildUp.vietmed_back.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import teamBuildUp.vietmed_back.domain.Tag;

@Data
@NoArgsConstructor
public class SaveTagRequest {

    private String tagField;
    private String tagValue;

    @Builder
    public SaveTagRequest(String tagField, String tagValue) {
        this.tagField = tagField;
        this.tagValue = tagValue;
    }

    public Tag makeTagEntity() {
        return Tag.createTag(
                this.tagField,
                this.tagValue
        );
    }
}
