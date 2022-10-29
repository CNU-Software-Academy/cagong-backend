package kr.ac.cnu.swacademy.cagong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SortedCafeDto implements Comparable<SortedCafeDto> {
    private Long id;
    private String name;
    private String address;
    private double distance;
    private double studyScore;
    private double averagePrice;
    private double averageScore;

    @Override
    public int compareTo(SortedCafeDto o) {
        return Double.compare(this.distance, o.distance);
    }
}
