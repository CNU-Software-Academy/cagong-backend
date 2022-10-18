package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.LocationDto;
import kr.ac.cnu.swacademy.cagong.dto.SortedCafeDto;
import kr.ac.cnu.swacademy.cagong.service.CafeDistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CafeDistanceApiController {
    private final CafeDistanceService cafeDistanceService;

    @GetMapping("/api/cafes/search/ldptj ocation")
    public List<SortedCafeDto> cafeList(@RequestParam double longitude,
                                        @RequestParam double latitude) {
        return cafeDistanceService.findAll(new LocationDto(longitude, latitude));
    }
}
