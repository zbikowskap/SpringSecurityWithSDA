package pl.sda.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {

    @GetMapping
    public String getAuthPage(){
        return "/auth/auth";
    }
}
