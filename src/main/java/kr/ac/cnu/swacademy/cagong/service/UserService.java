package kr.ac.cnu.swacademy.cagong.service;

import kr.ac.cnu.swacademy.cagong.entity.User;
import kr.ac.cnu.swacademy.cagong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public String join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return "Success";
    }

    private void validateDuplicateUser(User user){
        User findUser = userRepository.findByUsername(user.getUsername());
        if(findUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
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
