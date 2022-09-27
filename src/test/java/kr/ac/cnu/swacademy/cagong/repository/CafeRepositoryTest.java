package kr.ac.cnu.swacademy.cagong.repository;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import kr.ac.cnu.swacademy.cagong.entity.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CafeRepositoryTest {

    @Autowired
    CafeRepository cafeRepository;

    private Cafe cafe;

    @BeforeEach
    void setUp() {
        Review review = new Review();
        review.setContent("이집 커피 잘하네.");
        review.setImageUrl("");
        review.setClean(1);
        review.setConcentration(2);
        review.setSeat(3);

        cafe = new Cafe();
        cafe.setName("스타벅스 충남대 정문점");
        cafe.setAddress("대전 유성구 대학로 82");
        cafe.addReview(review);

        cafeRepository.save(cafe);
    }

    @AfterEach
    void tearDown() {
        cafeRepository.deleteAll();
    }

    @Test
    void 카페리스트_조회하기() {
        //when
        List<Cafe> cafes = cafeRepository.findAll();

        //then
        assertThat(cafes).hasSize(1);
        assertThat(cafes.get(0).getName()).isEqualTo(cafe.getName());
    }
}