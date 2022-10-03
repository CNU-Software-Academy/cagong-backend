package kr.ac.cnu.swacademy.cagong.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@NoArgsConstructor
@Table(name="reviews")
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @NotBlank
    @Size(min = 5, message = "최소 5글자를 입력해주세요.")
    @Column(length = 45)
    private String content;

    private String imageUrl;

    @NotNull
    private int clean;
    @NotNull
    private int seat;
    @NotNull
    private int concentration;

    public void setCafe(Cafe cafe) {
        if (Objects.nonNull(this.cafe)) {
            this.cafe.getReviews().remove(this);
        }

        this.cafe = cafe;
        cafe.getReviews().add(this);
    }
}
