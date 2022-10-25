package kr.ac.cnu.swacademy.cagong.service;

import kr.ac.cnu.swacademy.cagong.dto.AnswerDto.AnswerSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.dto.QuestionDto.QuestionListResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.QuestionDto.QuestionResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.QuestionDto.QuestionSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.dto.QuestionDto.QuestionUpdateRequestDto;
import kr.ac.cnu.swacademy.cagong.entity.Answer;
import kr.ac.cnu.swacademy.cagong.entity.Question;
import kr.ac.cnu.swacademy.cagong.repository.AnswerRepository;
import kr.ac.cnu.swacademy.cagong.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public Long save(QuestionSaveRequestDto requestDto) {
        return questionRepository.save(requestDto.toEntity()).getId();
    }

    public QuestionResponseDto findById(Long id) {
        Question entity = questionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 질문이 없습니다. id=" + id));
        return new QuestionResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<QuestionListResponseDto> findAllDesc() {
        return questionRepository.findAllDesc().stream()
                .map(QuestionListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, QuestionUpdateRequestDto requestDto) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 질문이 없습니다. id=" + id));
        question.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 질문이 없습니다. id = " + id));
        questionRepository.delete(question);
    }

    @Transactional
    public void commentSave(AnswerSaveRequestDto answerSaveRequestDto) {
        Answer answer;
        answer = new Answer(
                answerSaveRequestDto.getComment(),
                questionRepository.findById(answerSaveRequestDto.getQuestionId()).orElseThrow(() -> new IllegalArgumentException("질문 찾기 실패"))
        );
        answerRepository.save(answer);
    }

    @Transactional
    public void commentDelete(Long answerId) {
        answerRepository.deleteById(answerId);
    }
}
