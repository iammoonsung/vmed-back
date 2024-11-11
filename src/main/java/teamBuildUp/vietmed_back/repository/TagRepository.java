package teamBuildUp.vietmed_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamBuildUp.vietmed_back.domain.Product;
import teamBuildUp.vietmed_back.domain.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    public Tag findTagByTagFieldAndTagValue(String tagField, String tagValue);

    @Query("select pt.tag from ProductTag pt where pt.product =: product")
    public List<Tag> findByProduct(@Param("product") Product product);
}
