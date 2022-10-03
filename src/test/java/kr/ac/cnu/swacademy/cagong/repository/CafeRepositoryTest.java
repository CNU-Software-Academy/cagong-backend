package kr.ac.cnu.swacademy.cagong.repository;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CafeRepositoryTest {

    @Autowired
    CafeRepository cafeRepository;

    private Cafe cafe;

    @BeforeEach
    void setUp() {
        Random randomGenerator = new Random();

        cafe = Cafe.builder()
                .name("스타벅스 충남대 정문점")
                .address("대전 유성구 대학로 82")
                .latitude(randomGenerator.nextDouble())
                .longitude(randomGenerator.nextDouble())
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
        Optional<Cafe> givenCafe = cafeRepository.findByName(cafe.getName());

        assertThat(givenCafe).isNotEmpty();
        assertThat(givenCafe.get().getName()).isEqualTo(cafe.getName());
    }


}