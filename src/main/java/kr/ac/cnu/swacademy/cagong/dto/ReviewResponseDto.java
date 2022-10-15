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
    private int clean;
    private int seat;
    private int concentration;
    private String imageUrl;


    public ReviewResponseDto(Review review)
    {
        this.id = review.getId();
        this.userId = review.getUser().getId();
        this.cafeId = review.getCafe().getId();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
        this.content = review.getContent();
        this.clean = review.getClean();
        this.seat = review.getSeat();
        this.concentration = review.getConcentration();
        this.imageUrl = review.getImageUrl();
    }
}
