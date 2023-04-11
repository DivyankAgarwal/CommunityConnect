package ecc.project.community.connect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello IU! ";
    }


    @GetMapping("/name")
    public String getName(){
        return "CommunityConnect";
    }
}
