package kr.ac.cnu.swacademy.cagong.entity;

import kr.ac.cnu.swacademy.cagong.dto.UserFormDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, length = 45)
    private String username;

    @NotNull
    private String password;

    @Column(unique = true, length = 45)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder) {
        String username = userFormDto.getUsername();
        String password = passwordEncoder.encode(userFormDto.getPassword());
        Role role = getRole(username);
        return new User(username, password, userFormDto.getEmail(), role);
    }

    private static Role getRole(String name) {
        if (name.equals("admin")) {
            return Role.ADMIN;
        }
        return Role.USER;
    }
}