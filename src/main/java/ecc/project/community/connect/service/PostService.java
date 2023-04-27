package ecc.project.community.connect.service;

import ecc.project.community.connect.domain.Post;
import ecc.project.community.connect.domain.PostResource;
import ecc.project.community.connect.repository.PostRepository;
import ecc.project.community.connect.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    private SnsTopicService snsTopicService;

    private UserService userService;

    public PostResource saveNewPost(PostResource postResource, Long userId){
        var user = userRepository.findById(userId);
        if (user.isPresent()){
            var newPost = new Post(postResource, user.get());
            if (newPost.getPriority().equals(Boolean.TRUE)){
                System.out.println("High Alert. Sending immediate notification to every user.");
                snsTopicService.sendSnsNotificationToLambda(userService.getAllEmailAddress(), postResource.getPostText());
            }
            postRepository.save(newPost);
            return new PostResource(newPost);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id not found while creating post");

    }

    public List<PostResource> getAllPosts(){
        return postRepository.findAll().stream().map(PostResource::new).collect(Collectors.toList());
    }

}
