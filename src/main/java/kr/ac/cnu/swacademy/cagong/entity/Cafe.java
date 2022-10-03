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

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

    private Double averagePrice;

    /**
     * 네이버에서의 평점
     */
    private Double averageScoreInNaver;

    /**
     * 네이버에서 좌석이 좋아요를 선택한 사용자의 수
     */
    private Integer seatSelectionCountInNaver;

    /**
     * 네이버에서 집중하기 좋아요를 선택한 사용자의 수
     */
    private Integer concentrationSelectionCountInNaver;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Cafe(String name, String address, Double averagePrice, Double longitude, Double latitude, Double averageScoreInNaver, Integer seatSelectionCountInNaver, Integer concentrationSelectionCountInNaver) {
        this.name = name;
        this.address = address;
        this.averagePrice = averagePrice;
        this.longitude = longitude;
        this.latitude = latitude;
        this.averageScoreInNaver = averageScoreInNaver;
        this.seatSelectionCountInNaver = seatSelectionCountInNaver;
        this.concentrationSelectionCountInNaver = concentrationSelectionCountInNaver;
    }

    public void addReview(Review review) {
        review.setCafe(this);
    }
}
