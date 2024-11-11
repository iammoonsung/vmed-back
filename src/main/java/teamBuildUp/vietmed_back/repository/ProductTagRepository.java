package teamBuildUp.vietmed_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamBuildUp.vietmed_back.domain.Product;
import teamBuildUp.vietmed_back.domain.ProductTag;
import teamBuildUp.vietmed_back.domain.Tag;

import java.util.Optional;

public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {

    public Optional<ProductTag> findProductTagByProductAndTag(Product product, Tag tag);
}
