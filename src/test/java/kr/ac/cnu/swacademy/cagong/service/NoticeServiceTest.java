package kr.ac.cnu.swacademy.cagong.service;

import kr.ac.cnu.swacademy.cagong.dto.NoticeSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.entity.Notice;
import kr.ac.cnu.swacademy.cagong.repository.NoticeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 공지사항")
@ExtendWith(MockitoExtension.class)
class NoticeServiceTest {

    @InjectMocks
    private NoticeService noticeService;

    @Mock
    private NoticeRepository noticeRepository;

    @Test
    void 공지사항_입력() {
        //given
        NoticeSaveRequestDto dto = new NoticeSaveRequestDto("제목", "내용");
        Notice notice = dto.toEntity();
        given(noticeRepository.save(any(Notice.class))).willReturn(notice);

        //when
        noticeService.save(dto);

        //then
        then(noticeRepository).should().save(any(Notice.class));

    }

}