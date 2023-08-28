package evgesha.blps.lab1.scheduler.job;

import evgesha.blps.lab1.service.RecipeViewsService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopRecipesJob implements Job {

    @Autowired
    private RecipeViewsService recipeViewsService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        recipeViewsService.sendTopRecipesByViewsToMainService();
        log.info("top cron done");
    }
}
