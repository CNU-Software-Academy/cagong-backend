package kr.ac.cnu.swacademy.cagong.service;

import groovy.util.logging.Slf4j;
import kr.ac.cnu.swacademy.cagong.dto.ReviewListResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.ReviewResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.ReviewSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import kr.ac.cnu.swacademy.cagong.entity.Review;
import kr.ac.cnu.swacademy.cagong.entity.User;
import kr.ac.cnu.swacademy.cagong.repository.CafeRepository;
import kr.ac.cnu.swacademy.cagong.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    Logger logger = LoggerFactory.getLogger("logback");
    @InjectMocks
    ReviewService reviewService;
    
    @Mock
    ReviewRepository reviewRepository;

    @BeforeEach
    void set()
    {

    }

    @AfterEach
    void after()
    {

    }

    @Test
    void 리뷰를_저장한다()
    {
        // given
        ReviewSaveRequestDto requestDto = ReviewSaveRequestDto
                .builder()
                .clean(5)
                .concentration(5)
                .seat(5)
                .content("fewagewgawe")
                .imageUrl("aregerahershre")
                .build();
        User user = new User();
        Cafe cafe = Cafe.builder().build();

        Review review = requestDto.toEntity(user, cafe);
        given(reviewRepository.save(any(Review.class))).willReturn(review);
                
        // when
        reviewService.save(requestDto, user, cafe);

        // then
        then(reviewRepository).should().save(any(Review.class));
    }

    @Test
    void 리뷰를_전체_조회한다()
    {
        //given

        User user = new User();
        Cafe cafe = Cafe.builder().build();

        Review review1 = Review
                .builder()
                .user(user)
                .cafe(cafe)
                .content("hello world1")
                .build();
        Review review2 = Review
                .builder()
                .user(user)
                .cafe(cafe)
                .content("hello world2")
                .build();


        given(reviewRepository.findAll()).willReturn(List.of(review1, review2));

        //when
        List<ReviewListResponseDto> all = reviewService.findAll();

        //then
        then(reviewRepository).should().findAll();
        assertThat(all).hasSize(2);
    }

    @Test
    void 리뷰를_단건_조회한다()
    {
        //given

        User user = new User();
        Cafe cafe = Cafe.builder().build();

        Review review1 = Review
                .builder()
                .user(user)
                .cafe(cafe)
                .content("hello world1")
                .build();

        given(reviewRepository.findById(any(Long.class))).willReturn(Optional.of(review1));

        //when
        ReviewResponseDto responseDto = reviewService.findById(1L);

        //then
        then(reviewRepository).should().findById(any(Long.class));
        assertThat(new ReviewResponseDto(review1))
                .usingRecursiveComparison()
                .isEqualTo(responseDto);
    }
}