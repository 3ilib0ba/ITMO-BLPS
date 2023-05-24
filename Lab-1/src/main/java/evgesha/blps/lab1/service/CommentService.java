package evgesha.blps.lab1.service;

import evgesha.blps.lab1.dto.CommentDto;
import evgesha.blps.lab1.dto.CommentUserDto;
import evgesha.blps.lab1.entity.Comment;
import evgesha.blps.lab1.entity.User;
import evgesha.blps.lab1.mapper.CommentMapper;
import evgesha.blps.lab1.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AuthenticationService authenticationService;

    public CommentService(
            CommentRepository commentRepository,
            CommentMapper commentMapper, AuthenticationService authenticationService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.authenticationService = authenticationService;
    }

    public CommentUserDto postComment(CommentDto commentDto) {
        Optional<User> isUser = authenticationService.getUserFromAuth();

        Comment comment = commentMapper.fromDtoAndUser(commentDto, isUser);
        Comment result = commentRepository.save(comment);

        return commentMapper.toDto(result);
    }


}
