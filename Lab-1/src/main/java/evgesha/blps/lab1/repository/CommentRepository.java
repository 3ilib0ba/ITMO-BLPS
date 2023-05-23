package evgesha.blps.lab1.repository;

import evgesha.blps.lab1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {

}
