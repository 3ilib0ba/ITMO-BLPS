package evgesha.blps.lab1.camunda;

import evgesha.blps.lab1.entity.Comment;
import evgesha.blps.lab1.dto.CommentUserDto;
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
public class GetAllCommetnsDelegate {
    @Autowired
    private CommentService commentService;

    @JobWorker(type = "get_all_comments", autoComplete = false)
    public Map<String, Object> getAllComments(final JobClient client, final ActivatedJob job) {
        log.info("JOB: get all comments");

        Map<String, Object> results = new HashMap<>();

        List<CommentUserDto> deletedComments  = commentService.getAllComments();
        StringBuilder commentsToString = new StringBuilder("");
        for (CommentUserDto comment : deletedComments) {
            commentsToString.append(comment.toString()).append(" ");
        }
        results.put("all_commetns", commentsToString.toString());

        return results;
    }

}
