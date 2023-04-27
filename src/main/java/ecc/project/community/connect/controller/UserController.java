package ecc.project.community.connect.controller;

import ecc.project.community.connect.domain.LoginObject;
import ecc.project.community.connect.domain.User;
import ecc.project.community.connect.domain.UserPasswordHide;
import ecc.project.community.connect.domain.UserResource;
import ecc.project.community.connect.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/users/")
public class UserController {

    private UserService userService;

    @GetMapping()
    public List<UserResource> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping()
    public UserPasswordHide registerUser(@Valid @RequestBody UserResource userResource) {
        return userService.registerUser(userResource);
    }

    @PostMapping("login/")
    public UserPasswordHide login(@Valid @RequestBody LoginObject loginObject) {
        return userService.loginToSystem(loginObject);
    }

    @GetMapping("/getId")
    public Long getUserIdFromUserEmail(@Valid @RequestBody String email){
        return userService.getUserId(email);
    }

    @GetMapping("/hello")
    public String printHello(){
        return "Hello World";
    }

}
