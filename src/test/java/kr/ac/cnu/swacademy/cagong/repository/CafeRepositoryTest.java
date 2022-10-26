package kr.ac.cnu.swacademy.cagong.repository;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
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
        cafe = Cafe.builder()
                .name("이디야 대전유성온천역점(봉명동)")
                .address("34179 대전광역시 유성구 계룡로 92 cj나인파크 101-1호 이디야")
                .build();

        cafeRepository.save(cafe);
    }

    @AfterEach
    void tearDown() {
        cafeRepository.deleteAll();
    }

    @Test
    void 카페리스트_조회하기() {
        List<Cafe> cafes = cafeRepository.findAll();

        assertThat(cafes).hasSize(1);
        assertThat(cafes.get(0).getId()).isEqualTo(cafe.getId());
    }

    @Test
    void 카페이름으로_조회하기() {

        List<Cafe> givenCafe = cafeRepository.findByNameLike("%" + cafe.getName() + "%");

        assertThat(givenCafe).isNotEmpty();
        assertThat(givenCafe.get(0).getName()).isEqualTo(cafe.getName());
    }
}
