package teamBuildUp.vietmed_back.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class ProductTag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    private ProductTag (Product product, Tag tag) { this.tag = tag; }
    public static ProductTag createProductTag(Tag tag) {
        ProductTag productTag = new ProductTag();
        productTag.setTag(tag);

        return productTag;
    }


}
