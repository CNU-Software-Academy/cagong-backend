package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.CafeListResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.CafeResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.CafeSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CafeApiController {

    private final CafeService cafeService;

    @PostMapping("/api/cafe")
    public long save(@RequestBody CafeSaveRequestDto requestDto) {
        return cafeService.save(requestDto);
    }

    @GetMapping("/api/cafes")
    public List<CafeListResponseDto> findAllDesc() {
        return cafeService.findAllDesc();
    }

    @GetMapping("/api/cafes/search/keyword/{keyword}")
    public ResponseEntity<List<CafeResponseDto>> findByKeyword(@PathVariable String keyword, @RequestParam(name = "sortBy", defaultValue = "", required = false) String sortBy) {

        if(!List.of("", "average_score", "average_price", "study_score").contains(sortBy)) {
            throw new SortingNotFoundException(String.format("%s is misaligned. \nThe permissible sorting method are average_score and average_price.", sortBy));
        }
        return ResponseEntity.ok(cafeService.findByKeyword(keyword, sortBy));
    }

    @GetMapping("/api/cafe/{id}")
    public CafeResponseDto findById(@PathVariable Long id) {
        return cafeService.findById(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class SortingNotFoundException extends RuntimeException{

        public SortingNotFoundException(String message){
            super(message);
        }
    }
}
