package ecc.project.community.connect.controller;

import ecc.project.community.connect.service.ErrorLoggingService;
import ecc.project.community.connect.service.SnsTopicService;
import ecc.project.community.connect.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
public class MainController {

    private final ErrorLoggingService errorLoggingService;

    private final SnsTopicService snsTopicService;

    private final UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Indiana University! ";
    }


    @GetMapping("/name")
    public String getName(){
        snsTopicService.listSNSTopics();
        System.out.println(userService.getAllEmailAddress());
        return "CommunityConnect";
    }

//    @GetMapping("/error")
//    public void throwError(){
//        errorLoggingService.throwError();
//    }
}
