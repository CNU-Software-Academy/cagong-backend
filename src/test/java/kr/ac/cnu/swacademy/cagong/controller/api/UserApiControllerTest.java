package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.UserFormDto;
import kr.ac.cnu.swacademy.cagong.entity.User;
import kr.ac.cnu.swacademy.cagong.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserApiControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser(String username, String password){
        UserFormDto userFormDto = new UserFormDto();
        userFormDto.setEmail("test@email.com");
        userFormDto.setUsername(username);
        userFormDto.setPassword(password);
        User user = User.createUser(userFormDto, passwordEncoder);
        return userService.join(user);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception{
        String username = "홍길동";
        String password = "1234";
        this.createUser(username, password);
        mockMvc.perform(formLogin().userParameter("username")
                        .loginProcessingUrl("/users/login")
                        .user(username).password(password))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void loginFailTest() throws Exception{
        String username = "홍길동";
        String password = "1234";
        this.createUser(username, password);
        mockMvc.perform(formLogin().userParameter("username")
                        .loginProcessingUrl("/users/login")
                        .user(username).password("12345"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

}