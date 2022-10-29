package kr.ac.cnu.swacademy.cagong.dto;

import kr.ac.cnu.swacademy.cagong.entity.Review;
import lombok.Getter;

@Getter
public class MyPageResponseDto {

    private Long reviewId;
    private Long cafeId;
    private String cafeName;
    private String content;

    public MyPageResponseDto(Review review) {
        this.reviewId = review.getId();
        this.cafeId = review.getCafe().getId();
        this.cafeName = review.getCafe().getName();
        this.content = review.getContent();
    }
}
