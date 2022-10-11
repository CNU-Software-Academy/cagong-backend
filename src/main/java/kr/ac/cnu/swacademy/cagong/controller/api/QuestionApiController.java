package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.AnswerDto.AnswerSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.dto.AnswerDto.ResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.QuestionDto.QuestionResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.QuestionDto.QuestionSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.dto.QuestionDto.QuestionUpdateRequestDto;
import kr.ac.cnu.swacademy.cagong.service.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QuestionApiController {

    private final QuestionService questionService;

    @PostMapping("/api/v1/question")
    public Long save(@RequestBody QuestionSaveRequestDto requestDto) {
        return questionService.save(requestDto);
    }

    @PutMapping("/api/v1/question/{id}")
    public Long update(@PathVariable Long id, @RequestBody QuestionUpdateRequestDto requestDto) {
        System.out.println(requestDto.getContent());
        System.out.println(requestDto.getTitle());
        return questionService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/question/{id}")
    public Long delete(@PathVariable Long id) {
        questionService.delete(id);
        return id;
    }

    @PostMapping("/api/question/{question_id}/answer")
    public ResponseDto<Integer> answerSave(@RequestBody AnswerSaveRequestDto answerSaveRequestDto){
        questionService.댓글쓰기(answerSaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}


