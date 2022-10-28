package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.CafeResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.CafeSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CafeApiController {

    private final CafeService cafeService;

    @GetMapping("/api/cafes")
    public List<CafeResponseDto> findAll(@RequestParam(name = "sortBy", defaultValue = "", required = false) String sortBy){
        if (!List.of("", "average_score", "average_price", "study_score").contains(sortBy)) {
            throw new IllegalArgumentException(sortBy + "은 잘못된 정렬 방식입니다.");
        }
        return cafeService.findAllDesc(sortBy);
    }

    @PostMapping("/api/cafe")
    public long save(@RequestBody CafeSaveRequestDto requestDto) {
        return cafeService.save(requestDto);
    }

    @GetMapping("/api/cafe/{id}")
    public CafeResponseDto findById(@PathVariable Long id) {
        return cafeService.findById(id);
    }
}
