package com.example.applicationcontroller;
import model.Lane;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.LaneService;
import java.util.List;


@RestController
@RequestMapping(value = "/test")
public class TestController {


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTest() {
        return ResponseEntity.ok().build();
    }


}
