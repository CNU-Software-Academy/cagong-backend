package kr.ac.cnu.swacademy.cagong.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name="cafes")
@Entity
@Getter
public class Cafe extends BaseTimeEntity {

    @Id
    @Column(name = "cafe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100)
    private String name;

    @NotBlank
    @Column(length = 100)//이름 길어서 안들어가서 늘려줌
    private String address;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

    private Double averagePrice;

    private Double averageScore	;

    private String zone;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public Cafe(String name, String address, Double longitude, Double latitude, Double averagePrice, Double averageScore, String zone) {
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.averagePrice = averagePrice;
        this.averageScore = averageScore;
        this.zone = zone;
    }

    public void addReview(Review review) {
        review.setCafe(this);
    }
}
