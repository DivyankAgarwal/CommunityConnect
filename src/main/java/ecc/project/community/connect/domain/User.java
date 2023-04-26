package ecc.project.community.connect.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;


    private String password;

    private String apartmentNumber;

    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Post> postLists;
    

    public User(UserResource userResource) {
        this.id = userResource.getId();
        this.email = userResource.getEmail();
        this.password = userResource.getPassword();
        this.apartmentNumber = userResource.getApartmentNumber();
        this.username = userResource.getUsername();
        this.postLists = userResource.getPostResourceList()
                .stream()
                .map(postResource -> new Post(postResource, null))
                .collect(Collectors.toList());
    }

}
