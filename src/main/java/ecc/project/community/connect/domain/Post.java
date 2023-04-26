package ecc.project.community.connect.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postText;

    private LocalDateTime timestamp;

    private Boolean priority;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private User user;

    public Post(PostResource postResource, User user){
        this.id = postResource.getId();
        this.postText = postResource.getPostText();
        this.timestamp = LocalDateTime.now();
        this.priority = postResource.getPriority();
        this.user = user;
    }


}
