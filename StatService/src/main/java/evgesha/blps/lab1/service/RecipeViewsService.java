package evgesha.blps.lab1.service;

import evgesha.blps.lab1.entity.RecipeViews;
import evgesha.blps.lab1.repository.RecipeViewsRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.Objects;
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
    public void receive(final ObjectMessage message) throws JMSException {
        Object objectInput = SerializationUtils.deserialize((byte[]) message.getObject());
        System.out.println(("get new message OBJECT = " + objectInput));

        Long recipeId = Long.valueOf((String) Objects.requireNonNull(objectInput));

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

}
