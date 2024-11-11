package teamBuildUp.vietmed_back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamBuildUp.vietmed_back.domain.Product;
import teamBuildUp.vietmed_back.domain.ProductTag;
import teamBuildUp.vietmed_back.domain.Tag;
import teamBuildUp.vietmed_back.dto.request.SaveProductRequest;
import teamBuildUp.vietmed_back.dto.response.FindProductResponse;
import teamBuildUp.vietmed_back.dto.response.GetTagOfProductResponse;
import teamBuildUp.vietmed_back.dto.response.SaveProductResponse;
import teamBuildUp.vietmed_back.repository.ProductRepository;
import teamBuildUp.vietmed_back.repository.ProductTagRepository;
import teamBuildUp.vietmed_back.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;
    private final ProductTagRepository productTagRepository;

    @Transactional
    public SaveProductResponse saveProduct(SaveProductRequest saveProductRequest) {

        List<Tag> tags = new ArrayList<>();
        for (String bodyPart : saveProductRequest.getBodyParts()) {
            tags.add(tagRepository.findTagByTagFieldAndTagValue("bodyPart", bodyPart));
        }
        for (String nutrition : saveProductRequest.getNutritions()) {
            tags.add(tagRepository.findTagByTagFieldAndTagValue("nutrition", nutrition));
        }

        Product product = saveProductRequest.makeProductEntity();

        for(Tag tag : tags) {
            ProductTag productTag = ProductTag.createProductTag(tag);
            product.addProductTag(productTag);
        }

        productRepository.save(product);
        return new SaveProductResponse(product.getId());
    }

    public FindProductResponse findOne(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(NoSuchElementException:: new);
        return FindProductResponse.builder().product(product).build();
    }

    public List<FindProductResponse> findProducts() {
        System.out.println("findProducts 진입");
        List<Product> productList = productRepository.findAll();
        for(Product p: productList) {
            p.getProductTags().forEach(productTag ->
                    System.out.println(productTag.getTag().getTagValue()));
        }

        return productList.stream()
                .map(product -> FindProductResponse.builder()
                        .product(product)
                        .build())
                .collect(Collectors.toList());
    }

    public List<FindProductResponse> findProductsByTag(Long tagId) {
        System.out.println("findProductsByTag 진입");
//        if(findProductRequest.getFindType() != FindType.TAG_ID) {
//            throw new Exception();
//        }
        Tag tag = tagRepository.findById(tagId).orElseThrow(NoSuchElementException::new);
        List<Product> productList = productRepository.findByTag(tag);
        List<FindProductResponse> findProductResponseList = new ArrayList<>();
        productList.forEach(
                product -> findProductResponseList.add(
                        FindProductResponse.builder()
                                .product(product)
                                .build()
                )
        );
        return findProductResponseList;
    }


    //기출시한 상품이 어떠한 변화를 가지고 재출시되었을때
    //크롤링한 값을 어떤식으로 받을지 정한 후 수정해야함
//    @Transactional
//    public void updateProduct(Product product, List<Tag> tagList){
//
//        //기존 Product의 tag를 가져온다
//        List<Tag> originalTagList = productRepository.findProductTags(product);
//
//
//        //기존 originaltagList와 새 tagList를 비교해서 기존 List에 없는 tag들을 추출한다.
//        List<Tag> newTagList = tagList.stream().filter(n -> originalTagList.stream()
//                .noneMatch(Predicate.isEqual(n))).toList();
//
//        newTagList.forEach(tag -> ProductTag.createProductTag(product, tag));
//    }


    public GetTagOfProductResponse getTagOfProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(NoSuchElementException::new);

        return GetTagOfProductResponse.builder()
                .productTags(product.getProductTags())
                .build();
    }

    public List<FindProductResponse> searchProduct(String keyword) {
        List<Product> productList = productRepository.findByNameContaining(keyword);
        List<FindProductResponse> findProductResponseList = new ArrayList<>();
        productList.forEach(
                product -> findProductResponseList.add(
                        FindProductResponse.builder()
                                .product(product)
                                .build()
                )
        );
        return findProductResponseList;
    }

    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.delete(
                productRepository.findById(productId).orElseThrow(NoSuchElementException::new)
        );
    }
}
