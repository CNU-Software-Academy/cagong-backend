package kr.ac.cnu.swacademy.cagong.web.dto;

import kr.ac.cnu.swacademy.cagong.entity.Question;
import kr.ac.cnu.swacademy.cagong.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionSaveRequestDto {
    private String title;
    private String content;
    private User user;

    @Builder
    public QuestionSaveRequestDto(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Question toEntity(){
        return Question.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
