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

    @GetMapping("/api/cafes/search/location")
    public List<SortedCafeDto> cafeList(@RequestParam double longitude,
                                        @RequestParam double latitude) {
        return cafeDistanceService.findAll(new LocationDto(longitude, latitude));
    }

    @GetMapping("/api/cafes/search/location/study-score")
    public List<SortedCafeDto> cafeListOrderByStudyScore(@RequestParam double longitude,
                                                         @RequestParam double latitude) {
        return cafeDistanceService.findAllOrderByStudyScore(new LocationDto(longitude, latitude));
    }

    @GetMapping("/api/cafes/search/location/average-price")
    public List<SortedCafeDto> cafeListOrderByAveragePrice(@RequestParam double longitude,
                                                           @RequestParam double latitude) {
        return cafeDistanceService.findAllOrderByAveragePrice(new LocationDto(longitude, latitude));
    }

    @GetMapping("/api/cafes/search/location/average-score")
    public List<SortedCafeDto> cafeListOrderByAverageScore(@RequestParam double longitude,
                                                           @RequestParam double latitude) {
        return cafeDistanceService.findAllOrderByAverageScore(new LocationDto(longitude, latitude));
    }
}
