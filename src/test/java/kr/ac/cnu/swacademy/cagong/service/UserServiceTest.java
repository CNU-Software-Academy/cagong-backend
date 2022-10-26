package kr.ac.cnu.swacademy.cagong.service;

import kr.ac.cnu.swacademy.cagong.dto.UserFormDto;
import kr.ac.cnu.swacademy.cagong.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser() {
        UserFormDto userFormDto = new UserFormDto();
        userFormDto.setEmail("test@email.com");
        userFormDto.setUsername("홍길동");
        userFormDto.setPassword("1234");
        return User.createUser(userFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateUserTest() {
        User user1 = createUser();
        User user2 = createUser();
        userService.join(user1);
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            userService.join(user2);
        });
        assertThat(e.getMessage()).isEqualTo("이미 가입된 회원입니다.");
    }
}
