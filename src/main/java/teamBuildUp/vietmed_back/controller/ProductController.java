package teamBuildUp.vietmed_back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamBuildUp.vietmed_back.dto.request.SaveProductRequest;
import teamBuildUp.vietmed_back.dto.response.FindProductResponse;
import teamBuildUp.vietmed_back.dto.response.SaveProductResponse;
import teamBuildUp.vietmed_back.service.ProductService;
import teamBuildUp.vietmed_back.service.TagService;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final TagService tagService;


    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }
    //상품 상세 조회(단건)
    @GetMapping("/product/{productId}")
    //@Operation - swagger 추후에 연동한 후 주석 제거
    public ResponseEntity<FindProductResponse> findOneProduct(@PathVariable("productId") Long productId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return ResponseEntity.ok()
                .headers(headers)
                .body(productService.findOne(productId));
    }

    //상품 저장
    @PostMapping("/product/new")
    //@Operation - swagger 추후에 연동한 후 주석 제거
    public ResponseEntity<SaveProductResponse> saveProduct(@RequestBody SaveProductRequest saveProductRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return ResponseEntity.ok()
                .headers(headers)
                .body(productService.saveProduct(saveProductRequest));
    }

//    @PatchMapping("/product/{productId}")
//    //@Operation - swagger 추후에 연동한 후 주석 제거
//    public ResponseEntity<UpdateProductResponse> updateProduct(@PathVariable("productId") Long productId, @RequestBody UpdateProductRequest updateProductRequest) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(productService.updateProduct(productId, updateProductRequest));
//    }

    //상품 조회
    @GetMapping("/products")
    //@Operation - swagger 추후에 연동한 후 주석 제거
    public ResponseEntity<List<FindProductResponse>> findProducts() {
        System.out.println("## findProducts controller ##");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<FindProductResponse> findProductResponseList = productService.findProducts(); //전체 상품 조회

        return ResponseEntity.ok()
                .headers(headers)
                .body(findProductResponseList);
    }

    @GetMapping("/products/{tagId}")
    //@Operation - swagger 추후에 연동한 후 주석 제거
    public ResponseEntity<List<FindProductResponse>> findProductsByTag(@PathVariable("tagId") Long tagId) {
        System.out.println("## findProductsByTag controller ##");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<FindProductResponse> findProductResponseList = findProductResponseList = productService.findProductsByTag(tagId); //태그로 상품 조회

        return ResponseEntity.ok()
                .headers(headers)
                .body(findProductResponseList);
    }

//    @GetMapping("/products")
//    //추후 querydsl로 동적쿼리 - 태그검색/상품검색 동적으로 분류
//    public ResponseEntity<List<FindProductResponse>> searchProduct(@RequestParam(value = "keyword", required = false) String keyword) {
//        System.out.println("## searchProduct controller ##");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
//
//        List<FindProductResponse> findProductResponseList = findProductResponseList = productService.searchProduct(keyword); //태그로 상품 조회
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(findProductResponseList);
//    }

    @DeleteMapping("/product/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return;
    }
}
