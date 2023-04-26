package ecc.project.community.connect.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostResource {

    private Long id;

    private String postText;

    private Boolean priority;

    private LocalDateTime timestamp;

    public PostResource(Post post){
        this.id = post.getId();
        this.postText = post.getPostText();
        this.priority = post.getPriority();
        this.timestamp = post.getTimestamp();
    }
}
