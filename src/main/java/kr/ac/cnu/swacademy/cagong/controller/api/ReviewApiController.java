package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.ReviewSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.dto.ReviewUpdateRequestDto;
import kr.ac.cnu.swacademy.cagong.service.CafeService;
import kr.ac.cnu.swacademy.cagong.service.ReviewService;
import kr.ac.cnu.swacademy.cagong.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewApiController {
    private final ReviewService reviewService;

    @PostMapping("/api/v1/review")
    public ResponseEntity<Long> saveReview(@RequestBody ReviewSaveRequestDto requestDto) {
        Long savedId = reviewService.save(requestDto);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping("/api/v1/review/{reviewId}")
    public ResponseEntity<Long> updateReview(@PathVariable Long reviewId, @RequestBody ReviewUpdateRequestDto requestDto) {
        Long updatedId = reviewService.update(reviewId, requestDto);
        log.info("{}", requestDto.toString());
        return ResponseEntity.ok(updatedId);
    }

    @DeleteMapping("/api/v1/review/{reviewId}")
    public ResponseEntity<Long> deleteReview(@PathVariable Long reviewId) {
        reviewService.delete(reviewId);
        return ResponseEntity.ok(reviewId);
    }

}
