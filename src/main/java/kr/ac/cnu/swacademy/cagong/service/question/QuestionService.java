package kr.ac.cnu.swacademy.cagong.service.question;

import kr.ac.cnu.swacademy.cagong.repository.QuestionRepository;
import kr.ac.cnu.swacademy.cagong.web.dto.QuestionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Long save(QuestionSaveRequestDto requestDto){return questionRepository.save(requestDto.toEntity()).getId();}

}
