package teamBuildUp.vietmed_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamBuildUp.vietmed_back.domain.Product;
import teamBuildUp.vietmed_back.domain.Tag;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByNameContaining(String Keyword);
    @Query("select pt.product from ProductTag pt where pt.tag = :tag")
    public List<Product> findByTag(@Param("tag") Tag tag);

}
