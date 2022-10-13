package kr.ac.cnu.swacademy.cagong.dto;

import kr.ac.cnu.swacademy.cagong.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class ReviewListResponseDto {

    private Long id;
    private Long userId;
    private Long cafeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReviewListResponseDto(Review review)
    {
        this.id = review.getId();
        this.userId = review.getUser().getId();
        this.cafeId = review.getCafe().getId();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
    }


}
