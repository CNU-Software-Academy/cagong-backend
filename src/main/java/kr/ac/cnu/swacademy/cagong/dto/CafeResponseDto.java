package kr.ac.cnu.swacademy.cagong.dto;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import lombok.Getter;

@Getter
public class CafeResponseDto {
    private Long id;
    private String name;
    private String address;
    private Double longitude;
    private Double latitude;
    private Double averagePrice;
    private Double averageScoreInNaver;
    private Integer seatSelectionCountInNaver;
    private Integer concentrationSelectionCountInNaver;

    public CafeResponseDto(Cafe entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.longitude = entity.getLongitude();
        this.latitude = entity.getLatitude();
        this.averagePrice = entity.getAveragePrice();
        this.averageScoreInNaver = entity.getAverageScoreInNaver();
        this.seatSelectionCountInNaver = entity.getSeatSelectionCountInNaver();
        this.concentrationSelectionCountInNaver = entity.getConcentrationSelectionCountInNaver();
    }
}
