package teamBuildUp.vietmed_back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamBuildUp.vietmed_back.domain.Tag;
import teamBuildUp.vietmed_back.dto.request.FindTagRequest;
import teamBuildUp.vietmed_back.dto.request.SaveTagRequest;
import teamBuildUp.vietmed_back.dto.response.FindTagResponse;
import teamBuildUp.vietmed_back.dto.response.SaveTagResponse;
import teamBuildUp.vietmed_back.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public SaveTagResponse saveOneTag(SaveTagRequest saveTagRequest) throws NoSuchFieldException {
        Tag tag = saveTagRequest.makeTagEntity();
        if (tag.getTagValue() == null || tag.getTagField() == null) {
            throw new NoSuchFieldException();
        }
        Long tagId = tagRepository.save(tag).getId();
        return SaveTagResponse.builder().tagId(tagId).build();
    }

    @Transactional
    public List<SaveTagResponse> saveManyTags(List<SaveTagRequest> saveTagRequestList) {
        List<SaveTagResponse> saveTagResponseList = new ArrayList<>();
        saveTagRequestList.forEach(
                saveTagRequest -> saveTagResponseList.add(
                        SaveTagResponse
                                .builder()
                                .tagId(tagRepository.save(saveTagRequest.makeTagEntity()).getId())
                                .build()
                )
        );
        return saveTagResponseList;
    }

    @Transactional
    public void deleteTag(Long tagId) {
        tagRepository.delete(
                tagRepository.findById(tagId).orElseThrow(NoSuchElementException::new)
        );
    }

    public FindTagResponse findTagById(Long tagId) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(NoSuchElementException::new);
        return FindTagResponse.builder().tag(tag).build();
    }

    public FindTagResponse findTagByFieldAndValue(FindTagRequest findTagRequest) {
        Tag tag = tagRepository.findTagByTagFieldAndTagValue(findTagRequest.getTagField(), findTagRequest.getTagValue());
        return FindTagResponse.builder().tag(tag).build();
    }


    public List<FindTagResponse> findAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> FindTagResponse.builder()
                        .tag(tag)
                        .build())
                .collect(Collectors.toList());
    }
}
