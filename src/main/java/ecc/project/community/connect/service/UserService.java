package ecc.project.community.connect.service;

import ecc.project.community.connect.domain.LoginObject;
import ecc.project.community.connect.domain.User;
import ecc.project.community.connect.domain.UserPasswordHide;
import ecc.project.community.connect.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public UserPasswordHide registerUser(User user){

        String userEmail = user.getEmail();

        if (userRepository.findByEmail(userEmail).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        return getUserInfoWithoutPassword(userRepository.save(user));

    }

    public UserPasswordHide getUserInfoWithoutPassword(User user){
        return new UserPasswordHide(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getApartmentNumber(),
                user.getPostLists());
    }

    public UserPasswordHide loginToSystem(LoginObject loginObject) {
        Optional<User> userObject = userRepository.findByEmail(loginObject.getEmail());
        if (userObject.isPresent()){
            if(userRepository.getPasswordByEmail(loginObject.getEmail()).equals(loginObject.getPassword())){
                return getUserInfoWithoutPassword(userObject.get());
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found");
    }
}
