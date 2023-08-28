package evgesha.blps.lab1.camunda;

import evgesha.blps.lab1.dto.CommentDto;
import evgesha.blps.lab1.dto.CommentUserDto;
import evgesha.blps.lab1.entity.Comment;
import evgesha.blps.lab1.entity.User;
import evgesha.blps.lab1.mapper.CommentMapper;
import evgesha.blps.lab1.repository.CommentRepository;
import evgesha.blps.lab1.repository.UserRepository;
import evgesha.blps.lab1.service.CommentService;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CamundaCommentsDelegate {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

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

    @JobWorker(type = "get_all_comments", autoComplete = true)
    public Map<String, Object> getAllComments(final JobClient client, final ActivatedJob job) {
        log.info("JOB: get all comments ");

        Map<String, Object> results = new HashMap<>();
        List<CommentUserDto> deletedComments  = commentService.getAllComments();
        StringBuilder commentsToString = new StringBuilder("");
        for (CommentUserDto commentUserDto : deletedComments) {
            commentsToString.append(commentUserDto.toString()).append(" ");
        }
        results.put("all_comments", commentsToString.toString());

        return results;
    }

    @JobWorker(type = "post_comment", autoComplete = true)
    public Map<String, Object> postComment(final JobClient client, final ActivatedJob job,
                                           @Variable String text,
                                           @Variable Long recipe_id,
                                           @Variable Integer user_id
    ) {
        log.info("JOB: post new comment");
        log.info(text + " " + recipe_id);
        log.info("user_id {}", user_id);

        Map<String, Object> results = new HashMap<>();
        CommentUserDto newComment = postComment(new CommentDto(text, recipe_id), user_id);
        results.put("NEW_COMMENT", newComment.toString());

        return results;
    }

    public CommentUserDto postComment(CommentDto commentDto, Integer user_id) {
        Optional<User> isUser = userRepository.findById(user_id);

        Comment comment = commentMapper.fromDtoAndUser(commentDto, isUser);
        Comment result = commentRepository.save(comment);

        return commentMapper.toDto(result);
    }

    @JobWorker(type = "delete_comment", autoComplete = true)
    public Map<String, Object> deleteComment(final JobClient client, final ActivatedJob job,
                                             @Variable Long comment_id,
                                             @Variable Integer user_id) {
        log.info("JOB: delete comment  ");

        Map<String, Object> results = new HashMap<>();
        if (commentRepository.findById(comment_id).get().getUserId() == user_id) {
            results.put("deleted_comment", commentService.returnDeletedComment(comment_id).toString());
        } else {
            results.put("deleted_comment", "не вышло)");
        }

        return results;
    }

}
