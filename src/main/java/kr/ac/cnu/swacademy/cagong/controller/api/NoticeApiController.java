package kr.ac.cnu.swacademy.cagong.controller.api;

import kr.ac.cnu.swacademy.cagong.dto.NoticeSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class NoticeApiController {

    private final NoticeService noticeService;

    @PostMapping("/api/v1/notice")
    public long save(@RequestBody NoticeSaveRequestDto requestDto) {
        return noticeService.save(requestDto);
    }
}


