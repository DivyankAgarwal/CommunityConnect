package ecc.project.community.connect.controller;

import ecc.project.community.connect.domain.LoginObject;
import ecc.project.community.connect.domain.User;
import ecc.project.community.connect.domain.UserPasswordHide;
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
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping()
    public UserPasswordHide registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("login/")
    public UserPasswordHide login(@Valid @RequestBody LoginObject loginObject) {
        return userService.loginToSystem(loginObject);
    }

}
