package kr.ac.cnu.swacademy.cagong.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Notice extends BaseTimeEntity {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Builder
    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
