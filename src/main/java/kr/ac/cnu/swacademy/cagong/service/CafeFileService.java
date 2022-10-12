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
//        List<Cafe> cafes = new ArrayList<>();
        String line;

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)){

            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(",");
                Cafe cafe = new Cafe(arr[1],arr[2],Double.parseDouble(arr[3]),Double.parseDouble(arr[4]),Double.parseDouble(arr[5]),Double.parseDouble(arr[6]),arr[7]);
//                cafes.add(cafe);
                cafeRepository.save(cafe);
            }
        } catch (IOException | NullPointerException e) {
            log.error("findAll() IOException  error ", e);
        }
//        return cafes;
    }
}
