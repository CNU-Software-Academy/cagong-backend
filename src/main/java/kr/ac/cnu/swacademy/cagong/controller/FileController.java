package kr.ac.cnu.swacademy.cagong.controller;

import kr.ac.cnu.swacademy.cagong.service.CafeFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final CafeFileService cafeFileService;

    @GetMapping("/admin/cafeFile")
    public String cafeFile() {
        cafeFileService.save();
        return "ok";
    }
}
