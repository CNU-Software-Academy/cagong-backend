package kr.ac.cnu.swacademy.cagong.controller;

import kr.ac.cnu.swacademy.cagong.service.question.QuestionService;
import kr.ac.cnu.swacademy.cagong.dto.QuestionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/question/save")
    public String questionSave(Model model) {
        model.addAttribute("saveForm", new QuestionSaveRequestDto());
        return "question/saveForm";
    }
}
