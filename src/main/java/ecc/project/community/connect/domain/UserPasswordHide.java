package ecc.project.community.connect.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserPasswordHide {

    private Long id;

    private String email;

    private String username;

    private String apartmentNumber;

    private List<Post> postLists;

    public UserPasswordHide(Long id, String email, String username, String apartmentNumber, List<Post> postLists) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.apartmentNumber = apartmentNumber;
        this.postLists = postLists;
    }
}
