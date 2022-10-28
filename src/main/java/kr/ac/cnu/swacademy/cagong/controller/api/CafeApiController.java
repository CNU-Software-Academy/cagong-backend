package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.CafeResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.CafeSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CafeApiController {

    private final CafeService cafeService;

    @PostMapping("/api/cafe")
    public long save(@RequestBody CafeSaveRequestDto requestDto) {
        return cafeService.save(requestDto);
    }

    @GetMapping("/api/cafe/{id}")
    public CafeResponseDto findById(@PathVariable Long id) {
        return cafeService.findById(id);
    }
}
