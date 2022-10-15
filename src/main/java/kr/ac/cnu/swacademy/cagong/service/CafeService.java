package kr.ac.cnu.swacademy.cagong.service;

import kr.ac.cnu.swacademy.cagong.dto.CafeListResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.CafeResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.CafeSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import kr.ac.cnu.swacademy.cagong.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CafeService {
    private final CafeRepository cafeRepository;

    @Transactional(readOnly = true)
    public List<CafeListResponseDto> findAllDesc() {
        return cafeRepository.findAllDesc().stream()
                .map(CafeListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CafeResponseDto findById(Long id) {
        Cafe cafe = cafeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 카페가 존재하지않습니다. id=" + id));
        return new CafeResponseDto(cafe);
    }

    @Transactional
    public long save(CafeSaveRequestDto requestDto) {
        return cafeRepository.save(requestDto.toEntity()).getId();
    }

}
