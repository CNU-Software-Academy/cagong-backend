package kr.ac.cnu.swacademy.cagong.controller;

import kr.ac.cnu.swacademy.cagong.dto.ReviewListResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.ReviewResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.ReviewSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.service.CafeService;
import kr.ac.cnu.swacademy.cagong.service.ReviewService;
import kr.ac.cnu.swacademy.cagong.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static ognl.DynamicSubscript.all;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final CafeService cafeService;

    @GetMapping("/reviews")
    public String reviewList(Model model, @PageableDefault(size= 10) Pageable pageable)
    {
        Page<ReviewListResponseDto> all = reviewService.findAllDesc(pageable);
        model.addAttribute("reviewList", all);
        return "review/reviewList";
    }

    @GetMapping("/review/save")
    public String reviewSave(Model model )
    {
        ReviewSaveRequestDto requestDto = new ReviewSaveRequestDto();
        List<Long> userIdList = userService.findId();
        List<Long> cafeIdList = cafeService.findId();
        model.addAttribute("review", requestDto);
        model.addAttribute("userIdList", userIdList);
        model.addAttribute("cafeIdList", cafeIdList);
        return "review/saveForm";
    }

    @GetMapping("/review/{reviewId}")
    public String reviewDetail(@PathVariable Long reviewId, Model model) {
        ReviewResponseDto responseDto = reviewService.findById(reviewId);
        model.addAttribute("review", responseDto);
        return "review/detail";
    }

    @GetMapping("/review/update/{reviewId}")
    public String reviewUpdate(@PathVariable Long reviewId, Model model) {
        ReviewResponseDto responseDto = reviewService.findById(reviewId);
        List<Long> userIdList = userService.findId();
        List<Long> cafeIdList = cafeService.findId();
        model.addAttribute("review", responseDto);
        model.addAttribute("userIdList", userIdList);
        model.addAttribute("cafeIdList", cafeIdList);
        return "review/updateForm";
    }
}
