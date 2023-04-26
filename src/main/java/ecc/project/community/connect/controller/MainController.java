package ecc.project.community.connect.controller;

import ecc.project.community.connect.service.ErrorLoggingService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
public class MainController {

    private final ErrorLoggingService errorLoggingService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Indiana University! ";
    }


    @GetMapping("/name")
    public String getName(){
        return "CommunityConnect";
    }

//    @GetMapping("/error")
//    public void throwError(){
//        errorLoggingService.throwError();
//    }
}
