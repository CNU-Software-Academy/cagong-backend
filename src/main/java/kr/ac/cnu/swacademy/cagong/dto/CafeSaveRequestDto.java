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
    private Double longitude;
    private Double latitude;
    private Double averagePrice;
    private Double averageScoreInNaver;
    private Integer seatSelectionCountInNaver;
    private Integer concentrationSelectionCountInNaver;

    @Builder
    public CafeSaveRequestDto(String name ,
                              String address ,
                              Double longitude ,
                              Double latitude ,
                              Double averagePrice ,
                              Double averageScoreInNaver ,
                              Integer seatSelectionCountInNaver ,
                              Integer concentrationSelectionCountInNaver) {
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.averagePrice = averagePrice;
        this.averageScoreInNaver = averageScoreInNaver;
        this.seatSelectionCountInNaver = seatSelectionCountInNaver;
        this.concentrationSelectionCountInNaver = concentrationSelectionCountInNaver;
    }

    public Cafe toEntity() {
        return Cafe.builder()
                .name(name)
                .address(address)
                .longitude(longitude)
                .latitude(latitude)
                .averagePrice(averagePrice)
                .averageScoreInNaver(averageScoreInNaver)
                .seatSelectionCountInNaver(seatSelectionCountInNaver)
                .concentrationSelectionCountInNaver(concentrationSelectionCountInNaver)
                .build();
    }
}
