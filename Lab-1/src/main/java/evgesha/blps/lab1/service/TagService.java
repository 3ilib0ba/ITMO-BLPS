package evgesha.blps.lab1.service;

import evgesha.blps.lab1.entity.Tag;
import evgesha.blps.lab1.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;


    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag checkTagAndAddIfNoExist(String tagName) {
        Optional<Tag> result = getTagByName(tagName);
        if (result.isPresent()) {
            return result.get();
        }

        return tagRepository.save(new Tag(0, tagName));
    }

    public Optional<Tag> getTagByName(String tagName) {
        return Optional.ofNullable(tagRepository.getTagByName(tagName));
    }

}
