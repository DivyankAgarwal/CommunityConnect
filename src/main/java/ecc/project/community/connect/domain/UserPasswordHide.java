package ecc.project.community.connect.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Data
public class UserPasswordHide {

    private Long id;

    private String email;

    private String username;

    private String apartmentNumber;

    private List<PostResource> postLists;

    public UserPasswordHide(UserResource userResource) {
        this.id = userResource.getId();
        this.email = userResource.getEmail();
        this.username = userResource.getUsername();
        this.apartmentNumber = userResource.getApartmentNumber();
        this.postLists = userResource.getPostResourceList();
    }
}
