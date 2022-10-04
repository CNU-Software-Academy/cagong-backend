package kr.ac.cnu.swacademy.cagong.dto;

import kr.ac.cnu.swacademy.cagong.entity.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public QuestionResponseDto(Question entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdAt = entity.getCreatedAt();
    }
}
