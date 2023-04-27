package ecc.project.community.connect.service;

import ecc.project.community.connect.domain.*;
import ecc.project.community.connect.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<UserResource> getAllUsers(){
        return userRepository.findAll().stream().map(UserResource::new).collect(Collectors.toList());
    }


    public UserPasswordHide registerUser(UserResource userResource){

        String userEmail = userResource.getEmail();

        if (userRepository.findByEmail(userEmail).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        return getUserInfoWithoutPassword(userRepository.save(new User(userResource)));

    }

    public UserPasswordHide getUserInfoWithoutPassword(User user){
        UserPasswordHide obj = new UserPasswordHide();
        obj.setId(user.getId());
        obj.setEmail(user.getEmail());
        obj.setUsername(user.getUsername());
        obj.setPostLists(user.getPostLists().size() > 0 ? user.getPostLists().stream().map(PostResource::new).collect(Collectors.toList()) : null);
        obj.setApartmentNumber(user.getApartmentNumber());
        return obj;
    }

    public UserPasswordHide loginToSystem(LoginObject loginObject) {
        Optional<User> userObject = userRepository.findByEmail(loginObject.getEmail());
        if (userObject.isPresent()){
            if(userRepository.getPasswordByEmail(loginObject.getEmail()).equals(loginObject.getPassword())){
                System.out.println(userObject.get().getPostLists().stream().map(PostResource::new).collect(Collectors.toList()));
//                return getUserInfoWithoutPassword(userObject.get());
                return getUserInfoWithoutPassword(userObject.get());
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found");
    }

    public List<String> getAllEmailAddress(){
        return userRepository.getAllEmailAddress();
    }

    public Long getUserId(String email){
        return  userRepository.getIdFromEmail(email);
    }
}
