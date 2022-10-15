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
    private int clean;
    private int seat;
    private int concentration;
    private String imageUrl;

    @Builder
    public ReviewSaveRequestDto(
            String content,
            int clean,
            int seat,
            int concentration,
            String imageUrl
    ) {
        this.content = content;
        this.clean = clean;
        this.seat = seat;
        this.concentration = concentration;
        this.imageUrl = imageUrl;
    }

    public Review toEntity(User user, Cafe cafe) {
        return Review.builder()
                .user(user)
                .cafe(cafe)
                .content(content)
                .clean(clean)
                .seat(seat)
                .concentration(concentration)
                .imageUrl(imageUrl)
                .build();
    }
}
