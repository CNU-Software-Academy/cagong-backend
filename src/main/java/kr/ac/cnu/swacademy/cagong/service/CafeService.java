package kr.ac.cnu.swacademy.cagong.service;

import kr.ac.cnu.swacademy.cagong.dto.CafeListResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.CafeResponseDto;
import kr.ac.cnu.swacademy.cagong.dto.CafeSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import kr.ac.cnu.swacademy.cagong.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CafeService {
    private final CafeRepository cafeRepository;

    @Transactional(readOnly = true)
    public Page<CafeListResponseDto> findAllDesc(Pageable pageable) {
        return cafeRepository.findAllDesc(pageable)
                .map(CafeListResponseDto::new);
    }

    @Transactional(readOnly = true)
    public List<CafeResponseDto> findAllDesc(String sortBy) {
        List<Cafe> cafes = cafeRepository.findAllDesc();
        if (sortBy.equals("average_score")) {
            cafes.sort(Comparator.comparingDouble(Cafe::getAverageScore).reversed());
        }
        if (sortBy.equals("average_price")) {
            cafes.sort(Comparator.comparingDouble(Cafe::getAveragePrice));
        }
        if (sortBy.equals("study_score")) {
            cafes.sort(Comparator.comparingDouble(Cafe::getStudyScore).reversed());
        }
        return cafes.stream()
                .map(CafeResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CafeResponseDto> findByKeyword(String keyword, String sortBy) {
        if(keyword.equals("all")) {
            return findAllDesc(sortBy);
        }
        StringTokenizer st = new StringTokenizer(keyword, " ");
        List<Cafe> cafes = new ArrayList<>();

        while (st.hasMoreTokens()) {
            String keywordToken = st.nextToken();
            List<Cafe> cafesToken = cafeRepository.findByNameLike("%" + keywordToken + "%");
            if (cafes.isEmpty()) {
                cafes.addAll(cafesToken);
            } else {
                cafes.retainAll(cafesToken);
            }
        }
        if (sortBy.equals("average_score")) {
            cafes.sort(Comparator.comparingDouble(Cafe::getAverageScore).reversed());
        }
        if (sortBy.equals("average_price")) {
            cafes.sort(Comparator.comparingDouble(Cafe::getAveragePrice));
        }
        if (sortBy.equals("study_score")) {
            cafes.sort(Comparator.comparingDouble(Cafe::getStudyScore).reversed());
        }
        return cafes.stream()
                .map(CafeResponseDto::new)
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

    @Transactional
    public List<Long> findId() {
        return cafeRepository.findId();
    }
}