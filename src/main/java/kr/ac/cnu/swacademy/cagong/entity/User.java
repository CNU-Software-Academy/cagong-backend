package kr.ac.cnu.swacademy.cagong.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, length = 45)
    private String username;

    @NotBlank
    private String pw;

    @Column(unique = true, length = 45)
    private String email;

    @Builder
    public User(String username, String pw) {
        this.username = username;
        this.pw = pw;
    }
}
