package kr.ac.cnu.swacademy.cagong.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.cnu.swacademy.cagong.dto.CafeListResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.CafeResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.CafeSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import kr.ac.cnu.swacademy.cagong.service.CafeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CafeApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CafeService cafeService;

    private static final String BASE_URL = "/api";

    @WithMockUser(roles = "USER")
    @Test
    @DisplayName("[API][GET] 카페 리스트 조회")
    void findAllDescTest() throws Exception {
        // Given
        Cafe cafe = Cafe.builder()
                .name("이디야 대전유성온천역점(봉명동)")
                .address("34179 대전광역시 유성구 계룡로 92 cj나인파크 101-1호 이디야")
                .build();

        CafeResponseDto cafeResponseDto = new CafeResponseDto(cafe);

        given(cafeService.findAllDesc(""))
                .willReturn(List.of(cafeResponseDto));

        // When, Then
        mockMvc.perform(get(BASE_URL + "/cafes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].name").value(cafeResponseDto.getName()))
                .andExpect(jsonPath("$[0].address").value(cafeResponseDto.getAddress()))
                .andDo(MockMvcResultHandlers.print());
    }

    @WithMockUser(roles = "USER")
    @Test
    @DisplayName("[API][GET] 카페 상세 조회")
    void findByIdTest() throws Exception {
        // Given
        Cafe cafe = Cafe.builder()
                .name("이디야 대전유성온천역점(봉명동)")
                .address("34179 대전광역시 유성구 계룡로 92 cj나인파크 101-1호 이디야")
                .build();

        CafeResponseDto cafeResponseDto = new CafeResponseDto(cafe);

        given(cafeService.findById(any()))
                .willReturn(cafeResponseDto);

        // When, Then
        mockMvc.perform(get(BASE_URL + "/cafe/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(cafe.getName()))
                .andExpect(jsonPath("$.address").value(cafe.getAddress()))
                .andDo(MockMvcResultHandlers.print());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    @DisplayName("[API][GET] 카페 등록")
    void saveTest() throws Exception {
        // Given
        String name = "뚜레쥬르 대전(장대동)";
        String address = "34171 대전광역시 유성구 문화원로 2 뚜레쥬르";
        double longitude = 127.3368179;
        double latitude = 36.3617531;
        double averagePrice = 4113.333;
        double averageScore = 4.3;
        double studyScore = 0.005277045;
        int seatSelectionCount = 1;
        int concentrationSelectionCount = 1;
        int totalReviewCount = 379;
        String zone = "장대동";

        String body = new ObjectMapper().writeValueAsString(
                CafeSaveRequestDto.builder()
                        .name(name)
                        .address(address)
                        .longitude(longitude)
                        .latitude(latitude)
                        .averagePrice(averagePrice)
                        .averageScore(averageScore)
                        .studyScore(studyScore)
                        .seatSelectionCount(seatSelectionCount)
                        .concentrationSelectionCount(concentrationSelectionCount)
                        .totalReviewCount(totalReviewCount)
                        .zone(zone)
                        .build()
        );

        given(cafeService.save(any()))
                .willReturn(1L);

        // When, Then
        mockMvc.perform(post(BASE_URL + "/cafe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"))
                .andDo(MockMvcResultHandlers.print());
    }
}
