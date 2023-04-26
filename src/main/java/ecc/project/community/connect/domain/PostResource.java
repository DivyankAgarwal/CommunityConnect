package ecc.project.community.connect.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResource {

    private Long id;

    private String postText;

    private Boolean priority;

    public PostResource(Post post){
        this.id = post.getId();
        this.postText = post.getPostText();
        this.priority = post.getPriority();
    }
}
