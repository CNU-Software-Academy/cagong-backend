package kr.ac.cnu.swacademy.cagong.dto;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import kr.ac.cnu.swacademy.cagong.entity.Review;
import kr.ac.cnu.swacademy.cagong.entity.User;
import lombok.*;


@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReviewUpdateRequestDto {

    private String content;
    private Boolean seat;
    private Boolean concentration;
    private String imageUrl;
    private Long userId;
    private Long cafeId;
    private int totalGrade;

    @Builder
    public ReviewUpdateRequestDto(
            String content,
            int totalGrade,
            Boolean seat,
            Boolean concentration,
            String imageUrl,
            Long userId,
            Long cafeId
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
