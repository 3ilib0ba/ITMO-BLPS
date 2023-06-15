package evgesha.blps.lab1.service;

import evgesha.blps.lab1.entity.RecipeViews;
import evgesha.blps.lab1.exception.CantConvertToTextMessageException;
import evgesha.blps.lab1.repository.RecipeViewsRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Optional;


@Service
public class RecipeViewsService {
    private final RecipeViewsRepository recipeViewsRepository;

    public RecipeViewsService(
            RecipeViewsRepository recipeViewsRepository
    ) {
        this.recipeViewsRepository = recipeViewsRepository;
    }

    @JmsListener(destination = "STAT_COUNT_VIEWS")
    public void receive(final Message message) throws JMSException {
        Long recipeId = Long.valueOf(convertToTextMessage(message).getText());

        Optional<RecipeViews> isRecipeViews = recipeViewsRepository.findById(recipeId);
        RecipeViews recipeViews;
        if (isRecipeViews.isEmpty()) {
            recipeViewsRepository.save(new RecipeViews(recipeId, 1));
            return;
        }
        recipeViews = isRecipeViews.get();
        recipeViews.setViewsCount(recipeViews.getViewsCount() + 1);
        recipeViewsRepository.save(recipeViews);

    }

    private TextMessage convertToTextMessage(Message message) {
        if (message instanceof TextMessage textMessage) {
            return textMessage;
        }
        throw new CantConvertToTextMessageException();
    }

}
