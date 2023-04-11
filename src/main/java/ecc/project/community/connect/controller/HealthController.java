package ecc.project.community.connect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping(value = "/")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok().build();
    }
}
