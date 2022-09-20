package kr.ac.cnu.swacademy.cagong.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Table(name="cafes")
@Entity
public class Cafe extends BaseTimeEntity {

    @Id
    @Column(name = "cafe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 45)
    private String name;

    @NotBlank
    @Column(length = 45)
    private String address;

    private int mapX;
    private int mapY;
}
