package kr.ac.cnu.swacademy.cagong.dto;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeSaveRequestDto {
    private String name;
    private String address;
    private double longitude;
    private double latitude;
    private double averagePrice;
    private double averageScore;
    private double studyScore;
    private int seatSelectionCount;
    private int concentrationSelectionCount;
    private int totalReviewCount;
    private String zone;

    @Builder
    public CafeSaveRequestDto(String name,
                              String address,
                              double longitude,
                              double latitude,
                              double averagePrice,
                              double averageScore,
                              double studyScore,
                              int seatSelectionCount,
                              int concentrationSelectionCount,
                              int totalReviewCount,
                              String zone) {
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.averagePrice = averagePrice;
        this.averageScore = averageScore;
        this.studyScore = studyScore;
        this.seatSelectionCount = seatSelectionCount;
        this.concentrationSelectionCount = concentrationSelectionCount;
        this.totalReviewCount = totalReviewCount;
        this.zone = zone;
    }

    public Cafe toEntity() {
        return Cafe.builder()
                .name(name)
                .address(address)
                .longitude(longitude)
                .latitude(latitude)
                .averagePrice(averagePrice)
                .averageScore(averageScore)
                .studyScore(studyScore)
                .seatSelectionCount(seatSelectionCount)
                .concentrationSelectionCount(concentrationSelectionCount)
                .totalReviewCount(totalReviewCount)
                .zone(zone)
                .build();
    }
}
