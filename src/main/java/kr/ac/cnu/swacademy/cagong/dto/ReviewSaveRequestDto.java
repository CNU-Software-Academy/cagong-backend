package kr.ac.cnu.swacademy.cagong.dto;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import kr.ac.cnu.swacademy.cagong.entity.Review;
import kr.ac.cnu.swacademy.cagong.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
public class ReviewSaveRequestDto {

    private String content;
    private Boolean seat;
    private Boolean concentration;
    private int totalGrade;
    private String imageUrl;
    private Long userId;
    private Long cafeId;

    @Builder
    public ReviewSaveRequestDto(
            String content,
            int totalGrade,
            Boolean seat,
            Boolean concentration,
            String imageUrl,
            Long userId,
            Long cafeIds
    ) {
        this.content = content;
        this.totalGrade = totalGrade;
        this.seat = seat;
        this.concentration = concentration;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.cafeId = cafeId;
    }

    public Review toEntity(User user, Cafe cafe) {
        return Review.builder()
                .user(user)
                .cafe(cafe)
                .content(content)
                .totalGrade(totalGrade)
                .seat(seat)
                .concentration(concentration)
                .imageUrl(imageUrl)
                .build();
    }
}
