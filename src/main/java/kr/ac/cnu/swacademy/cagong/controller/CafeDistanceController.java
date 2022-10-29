package kr.ac.cnu.swacademy.cagong.controller;

import kr.ac.cnu.swacademy.cagong.dto.LocationDto;
import kr.ac.cnu.swacademy.cagong.service.CafeDistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class CafeDistanceController {
    private final CafeDistanceService cafeDistanceService;

    @GetMapping("/cafes/search/location")
    public String cafeList(@RequestParam double longitude,
                           @RequestParam double latitude,
                           Model model) {
        model.addAttribute("cafes", cafeDistanceService.findAll(new LocationDto(longitude, latitude)));
        return "cafe/cafeList";
    }

    @GetMapping("/cafes/search/location/study-score")
    public String cafeListOrderByStudyScore(@RequestParam double longitude,
                                            @RequestParam double latitude,
                                            Model model) {
        model.addAttribute("cafes", cafeDistanceService.findAllOrderByStudyScore(new LocationDto(longitude, latitude)));
        return "cafe/cafeList";
    }

    @GetMapping("/cafes/search/location/average-price")
    public String cafeListOrderByAveragePrice(@RequestParam double longitude,
                                              @RequestParam double latitude,
                                              Model model) {
        model.addAttribute("cafes", cafeDistanceService.findAllOrderByAveragePrice(new LocationDto(longitude, latitude)));
        return "cafe/cafeList";
    }

    @GetMapping("/cafes/search/location/average-score")
    public String cafeListOrderByAverageScore(@RequestParam double longitude,
                                              @RequestParam double latitude,
                                              Model model) {
        model.addAttribute("cafes", cafeDistanceService.findAllOrderByAverageScore(new LocationDto(longitude, latitude)));
        return "cafe/cafeList";
    }
}
