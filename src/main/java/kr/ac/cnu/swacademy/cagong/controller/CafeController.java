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

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/cafes")
    public String adminCafesPage(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<CafeListResponseDto> cafes = cafeService.findAllDesc(pageable);
        model.addAttribute("cafes", cafes);
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
