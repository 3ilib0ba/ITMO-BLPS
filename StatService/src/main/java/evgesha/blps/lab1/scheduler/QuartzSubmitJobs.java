package evgesha.blps.lab1.scheduler;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzSubmitJobs {
    private static final String CRON_EVERY_FIVE_MINUTES = "0 0/5 * ? * * *";

    @Bean(name = "topRecipes")
    public JobDetailFactoryBean jobMemberStats() {
        return QuartzConfig.createJobDetail(TopRecipesByViewsJob.class, "Top 3 recipes by views Job");
    }

    @Bean(name = "memberStatsTrigger")
    public SimpleTriggerFactoryBean triggerMemberStats(@Qualifier("topRecipes") JobDetail jobDetail) {
        return QuartzConfig.createTrigger(jobDetail, 20000, "Top recipes Trigger");
    }

}
