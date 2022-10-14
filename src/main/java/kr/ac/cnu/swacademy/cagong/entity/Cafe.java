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
    @Column(length = 100)
    private String address;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    private double averagePrice;

    /**
     * 네이버에서의 평점
     */
    private double averageScore;

    private double studyScore;

    /**
     * 네이버에서 좌석이 좋아요를 선택한 사용자의 수
     */
    private int seatSelectionCount;

    /**
     * 네이버에서 집중하기 좋아요를 선택한 사용자의 수
     */
    private int concentrationSelectionCount;

    private int totalReviewCount;

    private String zone;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Cafe(
                String name,
                String address,
                double latitude,
                double longitude,
                double averagePrice,
                double averageScore,
                double studyScore,
                int seatSelectionCount,
                int concentrationSelectionCount,
                int totalReviewCount,
                String zone) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.averagePrice = averagePrice;
        this.averageScore = averageScore;
        this.studyScore = studyScore;
        this.seatSelectionCount = seatSelectionCount;
        this.concentrationSelectionCount = concentrationSelectionCount;
        this.totalReviewCount = totalReviewCount;
        this.zone = zone;
    }

    public void addReview(Review review) {
        review.setCafe(this);
    }
}
