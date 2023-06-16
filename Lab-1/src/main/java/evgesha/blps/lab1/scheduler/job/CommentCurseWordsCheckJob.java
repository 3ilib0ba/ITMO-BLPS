package evgesha.blps.lab1.scheduler.job;

import evgesha.blps.lab1.entity.Comment;
import evgesha.blps.lab1.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CommentCurseWordsCheckJob implements Job {
    @Autowired
    private CommentService commentService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("cron about deleting bad words start");
        List<Comment> results = commentService.deleteAllCommentsWithBadWords();
        for (Comment comment : results) {
            log.info(comment.toString() + " has been deleted");
        }
        log.info("cron about deleting bad words done");
    }
}
