package kr.ac.cnu.swacademy.cagong.repository;

import kr.ac.cnu.swacademy.cagong.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*
     * Username으로 찾기
     * */
    User findByUsername(String Username);

    /*
     * id로 찾기
     * */
    Optional<User> findById(Long id);
}
