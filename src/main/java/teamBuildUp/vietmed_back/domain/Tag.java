package teamBuildUp.vietmed_back.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String tagField;
    private String tagValue;

    private void setTagField(String tagField) {
        this.tagField = tagField;
    }

    private void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public Tag(){}
    private Tag(String tagField, String tagValue) {
        this.tagField = tagField;
        this.tagValue = tagValue;
    }
    public static Tag createTag(String tagField, String tagValue) {
        return new Tag(tagField, tagValue);
    }


}
