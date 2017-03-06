package controller;

import base.RespMsg;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @RequestMapping("/test")
    public RespMsg test() {
        return new RespMsg("success");
    }
}
