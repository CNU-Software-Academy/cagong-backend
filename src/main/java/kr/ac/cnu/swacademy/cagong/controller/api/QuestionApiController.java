package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.QuestionResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.QuestionSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.dto.QuestionUpdateRequestDto;
import kr.ac.cnu.swacademy.cagong.service.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QuestionApiController {

    private final QuestionService questionService;

    @PostMapping("/api/v1/question")
    public long save(@RequestBody QuestionSaveRequestDto requestDto){
        return questionService.save(requestDto);
    }

    @PutMapping("/api/v1/question/{id}")
    public Long update(@PathVariable Long id, @RequestBody QuestionUpdateRequestDto requestDto){
        return questionService.update(id, requestDto);
    }
}
