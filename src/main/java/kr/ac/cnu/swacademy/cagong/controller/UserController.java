package kr.ac.cnu.swacademy.cagong.controller;

import kr.ac.cnu.swacademy.cagong.entity.User;
import kr.ac.cnu.swacademy.cagong.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody User user) {
        log.info("username = {}, password = {}", user.getUsername(), user.getPassword());
        if (userService.join(user).equals("Success")) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
