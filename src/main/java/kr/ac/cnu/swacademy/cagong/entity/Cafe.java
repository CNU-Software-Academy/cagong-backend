package kr.ac.cnu.swacademy.cagong.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Table(name="cafes")
@Entity
@Getter
@ToString
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

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Cafe(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // 나중에 구현
//    private Double longitude;
//    private Double latitude;

    public void addReview(Review review) {
        review.setCafe(this);
    }
}
