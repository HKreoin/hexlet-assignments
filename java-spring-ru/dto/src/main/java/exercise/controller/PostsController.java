package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RequestMapping("/posts")
@RestController
public class PostsController {
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable Long id) {
        var post = postRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Post with id " +  id + " not found"));
        var postDTO = new PostDTO();
        postDTO.setId(id);
        postDTO.setBody(post.getBody());
        postDTO.setTitle(post.getTitle());
        var comments = commentRepository
            .findByPostId(id)
            .stream()
            .map(this::toCommentDTO)
            .toList();
        postDTO.setComments(comments);
        return postDTO;
    }

    public CommentDTO toCommentDTO(Comment comment) {
        var commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        return commentDTO;
    }

    @GetMapping(path = "")
    public List<PostDTO> index() {
        var posts = postRepository
            .findAll()
            .stream()
            .map(this::toPostDTO)
            .toList();
        return posts;
    }

    public PostDTO toPostDTO(Post post) {
        var postDTO =new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        return postDTO;
    }
}
// END
