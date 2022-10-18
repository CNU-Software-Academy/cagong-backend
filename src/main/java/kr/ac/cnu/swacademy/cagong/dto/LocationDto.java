package kr.ac.cnu.swacademy.cagong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class LocationDto {
    private double longitude;
    private double latitude;
}
