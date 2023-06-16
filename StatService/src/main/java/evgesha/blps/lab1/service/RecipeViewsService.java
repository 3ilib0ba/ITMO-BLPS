package evgesha.blps.lab1.service;

import evgesha.blps.lab1.dto.TopRecipeViewsListDto;
import evgesha.blps.lab1.entity.RecipeViews;
import evgesha.blps.lab1.exception.RecipeNotFoundException;
import evgesha.blps.lab1.mapper.RecipeViewsMapper;
import evgesha.blps.lab1.repository.RecipeViewsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class RecipeViewsService {
    @Value("${jms.queue.top_recipes}")
    private String TOP_RECIPES;

    private final RecipeViewsRepository recipeViewsRepository;
    private final RecipeViewsMapper recipeViewsMapper;
    private final JmsTemplate jmsTemplate;

    public RecipeViewsService(
            RecipeViewsRepository recipeViewsRepository,
            RecipeViewsMapper recipeViewsMapper,
            JmsTemplate jmsTemplate
    ) {
        this.recipeViewsRepository = recipeViewsRepository;
        this.recipeViewsMapper = recipeViewsMapper;
        this.jmsTemplate = jmsTemplate;
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

    public Integer getViews(Long recipeId) {
        Optional<RecipeViews> recipeViews = recipeViewsRepository.findById(recipeId);
        if (recipeViews.isEmpty()) {
            throw  new RecipeNotFoundException();
        }
        return recipeViews.get().getViewsCount();
    }

    public void sendTopRecipesByViewsToMainService() {
        jmsTemplate.send(TOP_RECIPES, session ->
                session.createObjectMessage(SerializationUtils.serialize(getTopViews()))
        );
    }

    public TopRecipeViewsListDto getTopViews() {
        List<RecipeViews> result = recipeViewsRepository.findTop3ByOrderByViewsCountDesc();
        TopRecipeViewsListDto resultDto = recipeViewsMapper.toListDtoFromList(result);
        return resultDto;
    }

}
