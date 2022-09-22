package kr.ac.cnu.swacademy.cagong.service;

import kr.ac.cnu.swacademy.cagong.dto.NoticeSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeSaveRequestDto requestDto) {
        return noticeRepository.save(requestDto.toEntity()).getId();
    }
}