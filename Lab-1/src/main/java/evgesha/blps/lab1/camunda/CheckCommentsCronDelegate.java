package evgesha.blps.lab1.camunda;

import evgesha.blps.lab1.entity.Comment;
import evgesha.blps.lab1.service.CommentService;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckCommentsCronDelegate {
    @Autowired
    private CommentService commentService;

    @JobWorker(type = "cron_check_comments", autoComplete = true)
    public Map<String, Object> checkAllCommentsForBadWords(final JobClient client, final ActivatedJob job) {
        log.info("JOB: check comments for bad words");

        Map<String, Object> results = new HashMap<>();

        List<Comment> deletedComments  = commentService.deleteAllCommentsWithBadWords();
        StringBuilder commentsToString = new StringBuilder("");
        for (Comment comment : deletedComments) {
            commentsToString.append(comment.toString()).append(" ");
        }
        results.put("deleted_comments", commentsToString.toString());

        return results;
    }

}
