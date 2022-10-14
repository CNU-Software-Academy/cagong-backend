package kr.ac.cnu.swacademy.cagong.controller;

import kr.ac.cnu.swacademy.cagong.dto.ReviewListResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.ReviewSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static ognl.DynamicSubscript.all;

@Controller
@RequiredArgsConstructor
public class ReviewController {


    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public String reviewList(Model model)
    {
        List<ReviewListResponseDto> all = reviewService.findAll();
        model.addAttribute("reviewList", all);
        return "review/reviewList";
    }

    @GetMapping("/review/save")
    public String reviewSave(Model model )
    {
        ReviewSaveRequestDto requestDto = new ReviewSaveRequestDto();
        model.addAttribute("review", requestDto);
        return "review/saveForm";
    }
}
