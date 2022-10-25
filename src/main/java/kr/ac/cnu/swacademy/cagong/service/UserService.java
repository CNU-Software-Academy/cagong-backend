package kr.ac.cnu.swacademy.cagong.service;


import kr.ac.cnu.swacademy.cagong.entity.User;
import kr.ac.cnu.swacademy.cagong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.core.userdetails.User.*;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User join(User user) {
        validateDuplicateUser(user);
        return userRepository.save(user);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        return builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }
}
