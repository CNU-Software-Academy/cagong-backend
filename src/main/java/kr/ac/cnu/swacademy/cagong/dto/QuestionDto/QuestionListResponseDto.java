package kr.ac.cnu.swacademy.cagong.dto.QuestionDto;

import kr.ac.cnu.swacademy.cagong.entity.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionListResponseDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;

    public QuestionListResponseDto(Question entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.createdAt = entity.getCreatedAt();
    }
}
