package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.ReviewSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.dto.ReviewUpdateRequestDto;
import kr.ac.cnu.swacademy.cagong.service.CafeService;
import kr.ac.cnu.swacademy.cagong.service.ReviewService;
import kr.ac.cnu.swacademy.cagong.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.Arrays;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewApiController {
    private final ReviewService reviewService;
    private final UserService userService;

    @PostMapping("/api/v1/review")
    public ResponseEntity<Long> saveReview(@RequestBody ReviewSaveRequestDto requestDto) {
        log.info("{} {}", requestDto.getUserId(), requestDto.getCafeId());
        Long savedId = reviewService.save(requestDto);
        return ResponseEntity.ok(savedId);
    }

    @Transactional
    @PutMapping("/api/v1/review/{reviewId}")
    public ResponseEntity<Long> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewUpdateRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.info("session is null");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else {
            String sessionId = session.getId();
            Cookie cookie = Arrays.stream(request.getCookies())
                    .filter(c -> c.getValue().equals(sessionId))
                    .findAny()
                    .orElse(null);
            if(cookie == null) {
                log.info("cookie is null");
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            else {
                if(!cookie.getValue().equals(sessionId)) {
                    log.info("cookie is not same with session");
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            }
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if(!reviewService.authenticateUserName(name, reviewId)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Long updatedId = reviewService.update(reviewId, requestDto);
        return ResponseEntity.ok(updatedId);
    }

    @DeleteMapping("/api/v1/review/{reviewId}")
    public ResponseEntity<Long> deleteReview(
            @PathVariable Long reviewId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.info("session is null");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else {
            String sessionId = session.getId();
            Cookie cookie = Arrays.stream(request.getCookies())
                    .filter(c -> c.getValue().equals(sessionId))
                    .findAny()
                    .orElse(null);
            if(cookie == null) {
                log.info("cookie is null");
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            else {
                if(!cookie.getValue().equals(sessionId)) {
                    log.info("cookie is not same with session");
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            }
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if(!reviewService.authenticateUserName(name, reviewId)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        reviewService.delete(reviewId);
        return ResponseEntity.ok(reviewId);
    }

}
