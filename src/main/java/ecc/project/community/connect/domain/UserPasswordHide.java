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

    public UserPasswordHide(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.apartmentNumber = user.getApartmentNumber();
        this.postLists = user.getPostLists();
    }
}
