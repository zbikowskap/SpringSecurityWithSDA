package pl.sda.springsecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
   // @Secured("ROLE_USER")
    public String getIndex(){
        return "/index";
    }
}
