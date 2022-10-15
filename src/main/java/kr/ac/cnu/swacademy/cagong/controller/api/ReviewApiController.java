package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.ReviewSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.service.CafeService;
import kr.ac.cnu.swacademy.cagong.service.ReviewService;
import kr.ac.cnu.swacademy.cagong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewApiController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final CafeService cafeService;
    @PostMapping("/api/v1/review")
    public ResponseEntity<Long> saveReview(@RequestBody ReviewSaveRequestDto requestDto) {
        Long savedId = reviewService.save(requestDto);
        return ResponseEntity.ok(savedId);
    }

}