package kr.ac.cnu.swacademy.cagong.service;

import kr.ac.cnu.swacademy.cagong.dto.ReviewSaveRequestDto;
import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import kr.ac.cnu.swacademy.cagong.entity.User;
import kr.ac.cnu.swacademy.cagong.repository.CafeRepository;
import kr.ac.cnu.swacademy.cagong.repository.ReviewRepository;
import kr.ac.cnu.swacademy.cagong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewFileService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;
    private final File file = new File("review.csv");

    public void save() {
        String line;
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(",");
                ReviewSaveRequestDto requestDto = new ReviewSaveRequestDto(
                        arr[0],
                        Integer.parseInt(arr[1]),
                        Boolean.parseBoolean(arr[2]),
                        Boolean.parseBoolean(arr[3]),
                        arr[4],
                        Long.parseLong(arr[5]),
                        Long.parseLong(arr[6])
                );
                User user = userRepository
                        .findById(requestDto.getUserId())
                        .orElseThrow(() -> new IllegalArgumentException("해당하는 id의 유저가 없습니다"));
                Cafe cafe = cafeRepository
                        .findById(requestDto.getCafeId())
                        .orElseThrow(() -> new IllegalArgumentException("해당하는 id의 카페가 없습니다"));
                reviewRepository.save(requestDto.toEntity(user, cafe));
            }
        } catch (IOException | NullPointerException e) {
            log.error("findAll() IOException  error ", e);
        }
    }
}