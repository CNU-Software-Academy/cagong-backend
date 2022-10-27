package kr.ac.cnu.swacademy.cagong.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    @GetMapping("/users/login")
    public String loginUser() {
        return "/users/loginForm";
    }
}
