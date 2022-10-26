package kr.ac.cnu.swacademy.cagong.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "cafes")
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

    private double latitude;

    private double longitude;

    private double averagePrice;

    private double averageScore;

    private double studyScore;

    private int seatSelectionCount;

    private int concentrationSelectionCount;

    private int totalReviewCount;

    private String zone;

    private String description;

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
            String zone,
            String description
    ) {
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
        this.description = description;
    }

    public void addReview(Review review) {
        review.setCafe(this);
    }
}
