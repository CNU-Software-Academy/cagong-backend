package kr.ac.cnu.swacademy.cagong.controller;

import kr.ac.cnu.swacademy.cagong.dto.CafeListResponseDto;
import kr.ac.cnu.swacademy.cagong.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/cafes")
    public String adminCafesPage(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<CafeListResponseDto> cafes = cafeService.findAllDesc(pageable);
        model.addAttribute("cafes", cafes);
        model.addAttribute("isPageable", true);
        return "cafe/cafeList";
    }

    @GetMapping("/cafes/search/keyword/{keyword}")
    public String findByKeyword(Model model, @PathVariable String keyword, @RequestParam(name = "sortBy", defaultValue = "", required = false) String sortBy) {
        if (!List.of("", "average_score", "average_price", "study_score").contains(sortBy)) {
            throw new IllegalArgumentException(sortBy + "은 잘못된 정렬 방식입니다.");
        }
        model.addAttribute("cafes", cafeService.findByKeyword(keyword, sortBy));
        model.addAttribute("isPageable", false);
        return "cafe/cafeList";
    }

    @GetMapping("/cafe/{id}")
    public String adminCafeDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("cafe", cafeService.findById(id));
        return "cafe/detail";
    }

    @GetMapping("/cafe/save")
    public String adminCafeSavePage() {
        return "cafe/saveForm";
    }
}
