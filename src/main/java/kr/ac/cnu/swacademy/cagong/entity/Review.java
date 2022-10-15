package kr.ac.cnu.swacademy.cagong.entity;

import kr.ac.cnu.swacademy.cagong.dto.ReviewUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@NoArgsConstructor
@Table(name="reviews")
@Entity
@Getter
public class Review extends BaseTimeEntity {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @Setter
    @NotBlank
    @Size(min = 5, message = "최소 5글자를 입력해주세요.")
    @Column(length = 45)
    private String content;

    @Setter
    private String imageUrl;

    @Setter
    @NotNull
    private int clean;
    @Setter
    @NotNull
    private int seat;
    @Setter
    @NotNull
    private int concentration;

    @Builder
    public Review(int clean, int seat, int concentration, String content, String imageUrl, User user, Cafe cafe) {
        this.clean = clean;
        this.seat = seat;
        this.concentration = concentration;
        this.user = user;
        this.cafe = cafe;
        this.content = content;
        this.imageUrl = imageUrl;
    }
    
    public void setCafe(Cafe cafe) {
        if (Objects.nonNull(this.cafe)) {
            this.cafe.getReviews().remove(this);
        }

        this.cafe = cafe;
        cafe.getReviews().add(this);
    }

    public void update(ReviewUpdateRequestDto requestDto, User user, Cafe cafe) {
        this.user = user;
        this.cafe = cafe;
        this.imageUrl = requestDto.getImageUrl();
        this.seat = requestDto.getSeat();
        this.clean = requestDto.getClean();
        this.concentration = requestDto.getConcentration();
        this.content = requestDto.getContent();
    }
}
