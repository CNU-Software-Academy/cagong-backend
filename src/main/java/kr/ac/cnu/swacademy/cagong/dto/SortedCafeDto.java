package kr.ac.cnu.swacademy.cagong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SortedCafeDto implements Comparable<SortedCafeDto> {
    private Long id;
    private String name;
    private double distance;

    @Override
    public int compareTo(SortedCafeDto o) {
        return Double.compare(this.distance, o.distance);
    }
}
