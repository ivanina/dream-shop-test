package controller;

import base.ErrorRespMsg;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ErrorControllerImpl implements ErrorController {
    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = PATH)
    ErrorRespMsg error(HttpServletRequest request, HttpServletResponse response) {
        return new ErrorRespMsg(response.getStatus(),"ça va mal");
    }
}
