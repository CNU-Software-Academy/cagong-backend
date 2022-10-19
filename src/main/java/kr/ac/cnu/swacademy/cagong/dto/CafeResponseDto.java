package kr.ac.cnu.swacademy.cagong.dto;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CafeResponseDto {

    private Long id;
    private String name;
    private String address;
    private String zone;
    private double longitude;
    private double latitude;
    private double averagePrice;
    private double averageScore;
    private double studyScore;
    private int seatSelectionCount;
    private int concentrationSelectionCount;
    private int totalReviewCount;

    public CafeResponseDto(Cafe entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.zone = entity.getZone();
        this.longitude = entity.getLongitude();
        this.latitude = entity.getLatitude();
        this.averagePrice = entity.getAveragePrice();
        this.averageScore = entity.getAverageScore();
        this.studyScore = entity.getStudyScore();
        this.seatSelectionCount = entity.getSeatSelectionCount();
        this.concentrationSelectionCount = entity.getConcentrationSelectionCount();
        this.totalReviewCount = entity.getTotalReviewCount();
    }
    public CafeResponseDto(Cafe entity, Long id) {
        this.id = id;
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.zone = entity.getZone();
        this.longitude = entity.getLongitude();
        this.latitude = entity.getLatitude();
        this.averagePrice = entity.getAveragePrice();
        this.averageScore = entity.getAverageScore();
        this.studyScore = entity.getStudyScore();
        this.seatSelectionCount = entity.getSeatSelectionCount();
        this.concentrationSelectionCount = entity.getConcentrationSelectionCount();
        this.totalReviewCount = entity.getTotalReviewCount();
    }

}
