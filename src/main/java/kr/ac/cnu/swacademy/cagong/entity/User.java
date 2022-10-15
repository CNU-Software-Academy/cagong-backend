package kr.ac.cnu.swacademy.cagong.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Table(name="users")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotBlank
    @Column(unique = true, length = 45)
    @Setter
    private String username;

    @NotBlank
    @Setter
    private String pw;

    @Column(unique = true, length = 45)
    @Setter
    private String email;
}
