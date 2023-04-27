package ecc.project.community.connect.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserResource {

    private Long id;

    private String email;

    private String password;

    private String apartmentNumber;

    private String username;

    private List<PostResource> postResourceList;


    public UserResource(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.apartmentNumber = user.getApartmentNumber();
        this.username = user.getUsername();
        this.postResourceList =  user.getPostLists().stream().map(PostResource::new).collect(Collectors.toList());
    }
}
