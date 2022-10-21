package kr.ac.cnu.swacademy.cagong.dto;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import lombok.Getter;

@Getter
public class CafeListResponseDto {

    private Long id;
    private String name;
    private String address;

    public CafeListResponseDto(Cafe entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
    }
}
