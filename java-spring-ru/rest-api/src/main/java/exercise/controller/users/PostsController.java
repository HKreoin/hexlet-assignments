package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index(@PathVariable int id) {
        var page = posts.stream().filter(u -> u.getUserId() == id)
            .toList();
        return page;
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable int id, @RequestBody Post post) {
        post.setUserId(id);
        posts.add(post);
        return post;
    }
}
// END