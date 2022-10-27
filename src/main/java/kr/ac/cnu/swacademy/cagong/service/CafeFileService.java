package kr.ac.cnu.swacademy.cagong.service;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import kr.ac.cnu.swacademy.cagong.repository.CafeRepository;
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
public class CafeFileService {

    private final CafeRepository cafeRepository;
    private final File file = new File("cafe.csv");

    public void save() {
        String line;
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(",");
                Cafe cafe = new Cafe(
                        arr[0],
                        arr[1],
                        Double.parseDouble(arr[2]),
                        Double.parseDouble(arr[3]),
                        Double.parseDouble(arr[4]),
                        Double.parseDouble(arr[5]),
                        Double.parseDouble(arr[6]),
                        Integer.parseInt(arr[7]),
                        Integer.parseInt(arr[8]),
                        Integer.parseInt(arr[9]),
                        arr[10],
                        arr[11]
                );
                cafeRepository.save(cafe);
            }
        } catch (IOException | NullPointerException e) {
            log.error("findAll() IOException  error ", e);
        }
    }
}