package teamBuildUp.vietmed_back.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import teamBuildUp.vietmed_back.dto.request.SaveTagRequest;
import teamBuildUp.vietmed_back.dto.response.FindProductResponse;
import teamBuildUp.vietmed_back.dto.response.FindTagResponse;
import teamBuildUp.vietmed_back.dto.response.SaveTagResponse;
import teamBuildUp.vietmed_back.service.ProductService;
import teamBuildUp.vietmed_back.service.TagService;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {

    private final ProductService productService;
    private final TagService tagService;

    //태그 저장(단건)
    @PostMapping("/tag/new")
    //@Operation - swagger 추후에 연동한 후 주석 제거
    public ResponseEntity<SaveTagResponse> saveTag(@RequestBody SaveTagRequest saveTagRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        try {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(tagService.saveOneTag(saveTagRequest));
        } catch (NoSuchFieldException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Such Field");
        }
    }

    //태그 저장(다건)
    @PostMapping("/tags/new")
    //@Operation - swagger 추후에 연동한 후 주석 제거
    public ResponseEntity<List<SaveTagResponse>> saveTag(@RequestBody List<SaveTagRequest> saveTagRequestList) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(tagService.saveManyTags(saveTagRequestList));
    }

    //태그 조회(단건)
    @GetMapping("/tag/{tagId}")
    //@Operation - swagger 추후에 연동한 후 주석 제거
    public ResponseEntity<FindTagResponse> findOneTag(@PathVariable("tagId") Long tagId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(tagService.findTagById(tagId));
    }

    //태그 조회(다건)
    @GetMapping("/tags")
    //@Operation - swagger 추후에 연동한 후 주석 제거
    public ResponseEntity<List<FindTagResponse>> findTags() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(tagService.findAllTags());
    }

    @DeleteMapping("/tag/{tagId}")
    public void deleteTag(@PathVariable("tagId") Long tagId) {
        tagService.deleteTag(tagId);
        return;
    }
}
