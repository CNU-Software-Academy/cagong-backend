package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.UserFormDto;
import kr.ac.cnu.swacademy.cagong.entity.User;
import kr.ac.cnu.swacademy.cagong.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public ResponseEntity join(@Valid @RequestBody UserFormDto userFormDto) {
        log.info("username = {}, password = {}", userFormDto.getUsername(), userFormDto.getPassword());
        User user = User.createUser(userFormDto, passwordEncoder);
        userService.join(user);
        return new ResponseEntity(HttpStatus.CREATED);
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
