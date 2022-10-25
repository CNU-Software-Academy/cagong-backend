package kr.ac.cnu.swacademy.cagong.service;

import kr.ac.cnu.swacademy.cagong.entity.User;
import kr.ac.cnu.swacademy.cagong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public String join(User user) {
        userRepository.save(User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build());
        return "Success";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        log.info("db password = {}, input password = {}", user.getPassword(), password);
        if (user.getPassword().equals(password)) {
            return "Success";
        }
        return "Failed";
    }
}
