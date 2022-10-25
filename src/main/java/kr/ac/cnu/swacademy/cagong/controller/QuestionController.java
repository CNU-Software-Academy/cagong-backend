package kr.ac.cnu.swacademy.cagong.controller;

import kr.ac.cnu.swacademy.cagong.dto.QuestionDto.QuestionSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/question")
    public String questionList(Model model) {
        model.addAttribute("questionList", questionService.findAllDesc());
        return "question/questionList";
    }

    @GetMapping("/question/{id}")
    public String question(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionService.findById(id));
        return "question/detail";
    }

    @GetMapping("/question/save")
    public String questionSave(Model model) {
        model.addAttribute("saveForm", new QuestionSaveRequestDto());
        return "question/saveForm";
    }

    @GetMapping("/question/update/{id}")
    public String questionUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionService.findById(id));
        return "question/updateForm";
    }
}
