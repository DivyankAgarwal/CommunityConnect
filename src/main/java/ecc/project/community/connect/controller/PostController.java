package ecc.project.community.connect.controller;

import ecc.project.community.connect.domain.PostResource;
import ecc.project.community.connect.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/posts/")
public class PostController {

    private PostService postService;

    @PostMapping("{userId}/")
    public PostResource saveNewPost(@Valid @RequestBody PostResource postResource,
                                    @PathVariable Long userId){
        return postService.saveNewPost(postResource, userId);
    }

    @GetMapping()
    public List<PostResource> getAllPosts(){
        return postService.getAllPosts();
    }
}
