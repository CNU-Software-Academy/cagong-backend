package kr.ac.cnu.swacademy.cagong.service.question;

import kr.ac.cnu.swacademy.cagong.dto.QuestionListResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.QuestionResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.QuestionUpdateRequestDto;
import kr.ac.cnu.swacademy.cagong.entity.Question;
import kr.ac.cnu.swacademy.cagong.repository.QuestionRepository;
import kr.ac.cnu.swacademy.cagong.dto.QuestionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Long save(QuestionSaveRequestDto requestDto) {
        return questionRepository.save(requestDto.toEntity()).getId();
    }

    public QuestionResponseDto findById(Long id){
        Question entity = questionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 질문이 없습니다. id=" + id));
        return new QuestionResponseDto(entity);
    }


    @Transactional(readOnly = true)
    public List<QuestionListResponseDto> findAllDesc(){
        return questionRepository.findAllDesc().stream()
                .map(QuestionListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, QuestionUpdateRequestDto requestDto) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 질문이 없습니다. id="+id));
        question.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 질문이 없습니다. id = " + id));
        questionRepository.delete(question);
    }
}
