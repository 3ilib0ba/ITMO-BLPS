package evgesha.blps.lab1.service;

import evgesha.blps.lab1.dto.LikeDto;
import evgesha.blps.lab1.entity.Like;
import evgesha.blps.lab1.repository.LikeRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecipeLikesService {
    private final LikeRepository likeRepository;

    public RecipeLikesService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @JmsListener(destination = "STAT_LIKES_RECIPES")
    public void receive(final ObjectMessage message) throws JMSException {
        Object objectInput = SerializationUtils.deserialize((byte[]) message.getObject());
        System.out.println(("get new like OBJECT = " + objectInput));
        LikeDto likeInfo = (LikeDto) Objects.requireNonNull(objectInput);

        Optional<Like> isLike = likeRepository.findByRecipeIdAndUserId(likeInfo.getRecipeId(), likeInfo.getUserId());
        if (isLike.isPresent()) {
            Like like = isLike.get();
            like.setIsLike(!like.getIsLike());

            likeRepository.save(like);
        } else {
            Like result = new Like(likeInfo.getRecipeId(), likeInfo.getUserId(), true);
            likeRepository.save(result);
        }
    }
}
