package kr.ac.cnu.swacademy.cagong.dto;

import kr.ac.cnu.swacademy.cagong.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {

    private Long id;
    private Long userId;
    private Long cafeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String content;
    private String imageUrl;
    private Boolean seat;
    private Boolean concentration;
    private int totalGrade;



    public ReviewResponseDto(Review review)
    {
        this.id = review.getId();
        this.userId = review.getUser().getId();
        this.cafeId = review.getCafe().getId();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
        this.content = review.getContent();
        this.seat = review.getSeat();
        this.concentration = review.getConcentration();
        this.imageUrl = review.getImageUrl();
        this.totalGrade = review.getTotalGrade();
    }
}
