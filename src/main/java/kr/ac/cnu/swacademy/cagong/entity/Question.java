package kr.ac.cnu.swacademy.cagong.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Question extends BaseTimeEntity {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(length = 45)
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"question"})
    @OrderBy("id desc")
    private List<Answer> answers;

    @Builder
    public Question(User user, String title, String content){
        this.user = user;
        this.title = title;
        this.content = content;
    }

    //  수정
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}