package kr.ac.cnu.swacademy.cagong.service;

import groovy.transform.builder.Builder;
import javassist.NotFoundException;
import kr.ac.cnu.swacademy.cagong.dto.CafeResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.CafeSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import kr.ac.cnu.swacademy.cagong.repository.CafeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;

@DisplayName("카페 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class CafeServiceTest {

    @Spy
    @InjectMocks
    private CafeService cafeService;

    @Mock
    private CafeRepository cafeRepository;

    private Long fakeCafeId = 1L;
    private String fakeCafeKeyword = "";

    private Cafe cafe;

    @BeforeEach
    public void setUp() throws AuthenticationException {
        // given
        cafe = Cafe.builder()
                .build();
    }

    @Test
    @DisplayName("모든 카페목록을 조회할 수 있다.")
    void findAllDescTest(){
        // given




        // when

        // then
    }

    @Test
    @DisplayName("키워드로 카페를 검색할 수 있다.")
    void findByKeywordTest() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("ID로 카페를 상세 조회할 수 있다.")
    void findByIdTest() throws IllegalArgumentException{
        // given
        CafeResponseDto cafeResponseDto = new CafeResponseDto(cafe, fakeCafeId);
        given(cafeRepository.findById(fakeCafeId)).willReturn(Optional.ofNullable(cafe));
        doReturn(cafeResponseDto).when(cafeService).findById(fakeCafeId);

        // when
        CafeResponseDto findCafe = cafeService.findById(fakeCafeId);

        // then
        Optional<Cafe> cafe = cafeRepository.findById(fakeCafeId);
        assertThat(findCafe, allOf(
                notNullValue(),
                samePropertyValuesAs(new CafeResponseDto(cafe.get(), fakeCafeId))));
    }

    @Test
    @DisplayName("카페 정보를 저장할 수 있다.")
    void saveTest() {
        // given
        CafeSaveRequestDto saveRequestDto = new CafeSaveRequestDto
                ("뚜레쥬르 대전(장대동)", "34171 대전광역시 유성구 문화원로 2 뚜레쥬르", 127.3368179, 36.3617531, 4113.333, 4.3, 0.005277045, 1, 1, 379, "장대동");
        Cafe cafe = saveRequestDto.toEntity();
        given(cafeRepository.save(any(Cafe.class))).willReturn(cafe);

        // when
        cafeService.save(saveRequestDto);

        // then
        then(cafeRepository).should().save(any(Cafe.class));
    }
}
