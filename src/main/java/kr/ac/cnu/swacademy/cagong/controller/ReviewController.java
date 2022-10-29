package kr.ac.cnu.swacademy.cagong.controller;

import kr.ac.cnu.swacademy.cagong.dto.ReviewListResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.ReviewResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.ReviewSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.service.CafeService;
import kr.ac.cnu.swacademy.cagong.service.ReviewService;
import kr.ac.cnu.swacademy.cagong.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/mypage")
    public String myPage(Model model, Principal principal) {
        model.addAttribute("reviews", reviewService.myPage(principal.getName()));
        return "review/myPage";
    }

    @GetMapping("/reviews")
    public String reviewList(Model model, @PageableDefault(size= 10) Pageable pageable)
    {
        Page<ReviewListResponseDto> all = reviewService.findAllDesc(pageable);
        model.addAttribute("reviewList", all);
        return "review/reviewList";
    }

    @GetMapping("/review/save/{cafeId}")
    public String reviewSave(Model model ,
                             @PathVariable Long cafeId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        if(name == "anonymousUser") {
            return "redirect:/users/login";
        }

        Long userId = userService.findIdByUserName(name);
        ReviewSaveRequestDto requestDto = new ReviewSaveRequestDto();

        model.addAttribute("review", requestDto);
        model.addAttribute("userId", userId);
        model.addAttribute("cafeId", cafeId);

        return "review/saveForm";
    }

    @GetMapping("/review/{reviewId}")
    public String reviewDetail(@PathVariable Long reviewId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Long userId = userService.findIdByUserName(userName);
        ReviewResponseDto responseDto = reviewService.findById(reviewId);
        model.addAttribute("review", responseDto);
        model.addAttribute("userId", userId);
        return "review/detail";
    }

    @GetMapping("/review/update/{reviewId}")
    public String reviewUpdate(@PathVariable Long reviewId, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        if(name == "anonymousUser") {
            return "redirect:/users/login";
        }

        ReviewResponseDto responseDto = reviewService.findById(reviewId);

        model.addAttribute("review", responseDto);
        model.addAttribute("userId", responseDto.getUserId());
        model.addAttribute("cafeId", responseDto.getCafeId());
        return "review/updateForm";
    }
}
