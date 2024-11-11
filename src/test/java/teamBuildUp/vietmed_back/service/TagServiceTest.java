package teamBuildUp.vietmed_back.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import teamBuildUp.vietmed_back.domain.Tag;
import teamBuildUp.vietmed_back.dto.request.SaveTagRequest;
import teamBuildUp.vietmed_back.dto.response.SaveTagResponse;
import teamBuildUp.vietmed_back.repository.TagRepository;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class TagServiceTest {

    @Autowired
    EntityManager em;

    @Autowired ProductService productService;
    @Autowired TagService tagService;
    @Autowired TagRepository tagRepository;

    @Test
    public void 태그단건등록() throws Exception {
        //given
        SaveTagRequest saveTagRequest = SaveTagRequest.builder().tagField("bodyPart").tagValue("관절").build();

        //when
        SaveTagResponse saveTagResponse = tagService.saveOneTag(saveTagRequest);

        //then
        Tag getTag = tagRepository.findById(saveTagResponse.getTagId()).orElseThrow();

        System.out.println("저장된 tag field = " + getTag.getTagField());
        System.out.println("저장된 tag value = " + getTag.getTagValue());
        assertEquals(saveTagResponse.getTagId(), getTag.getId());

    }

    @Test
    void saveManyTags() {
    }

    @Test
    void deleteTag() {
    }

    @Test
    void findTagById() {
    }

    @Test
    void findTagByFieldAndValue() {
    }

    @Test
    void findAllTags() {
    }
}