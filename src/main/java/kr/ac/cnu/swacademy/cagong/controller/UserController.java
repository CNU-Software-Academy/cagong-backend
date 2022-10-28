package kr.ac.cnu.swacademy.cagong.controller;

import kr.ac.cnu.swacademy.cagong.dto.UserFormDto;
import kr.ac.cnu.swacademy.cagong.entity.User;
import kr.ac.cnu.swacademy.cagong.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("userFormDto", new UserFormDto());
        return "users/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model) {
        log.info("username = {}, password = {}", userFormDto.getUsername(), userFormDto.getPassword());
        if(bindingResult.hasErrors()){
            return "users/joinForm";
        }
        try {
            User user = User.createUser(userFormDto, passwordEncoder);
            userService.join(user);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "users/joinForm";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "users/loginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "users/loginForm";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user, HttpServletRequest request) {
        log.info("username = {}, password = {}", user.getUsername(), user.getPassword());
        if (userService.login(user.getUsername(), user.getPassword()).equals("Success")) {
            return new ResponseEntity(HttpStatus.OK);
        }
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        // 세션에 로그인한 회원 정보 보관
        session.setAttribute("loginMember", request);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
