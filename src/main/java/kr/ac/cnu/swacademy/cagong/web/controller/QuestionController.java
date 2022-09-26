package kr.ac.cnu.swacademy.cagong.web.controller;

import kr.ac.cnu.swacademy.cagong.service.question.QuestionService;
import kr.ac.cnu.swacademy.cagong.web.dto.QuestionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("api/v1/question")
    public long save(@RequestBody QuestionSaveRequestDto requestDto){return questionService.save(requestDto);}


}
