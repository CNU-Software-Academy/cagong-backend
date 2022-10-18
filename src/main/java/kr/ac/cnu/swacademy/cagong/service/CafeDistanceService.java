package kr.ac.cnu.swacademy.cagong.service;

import kr.ac.cnu.swacademy.cagong.dto.LocationDto;
import kr.ac.cnu.swacademy.cagong.dto.SortedCafeDto;
import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import kr.ac.cnu.swacademy.cagong.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CafeDistanceService {

    private final CafeRepository cafeRepository;

    @Transactional(readOnly = true)
    public List<SortedCafeDto> findAll(LocationDto locationDto) {
        List<Cafe> cafes = cafeRepository.findAll();
        List<SortedCafeDto> sortedCafes = new ArrayList<>();
        addCafe(locationDto, cafes, sortedCafes);
        return sortedCafes;
    }

    private void addCafe(LocationDto locationDto, List<Cafe> cafes, List<SortedCafeDto> sortedCafes) {
        for (Cafe cafe : cafes) {
            double distance = distance(locationDto.getLatitude(), locationDto.getLongitude(), cafe.getLatitude(), cafe.getLongitude());
            filterOneKm(sortedCafes, cafe, distance);
            Collections.sort(sortedCafes);
        }
    }

    private void filterOneKm(List<SortedCafeDto> sortedCafes, Cafe cafe, double distance) {
        if (distance < 1000) {
            sortedCafes.add(new SortedCafeDto(cafe.getId(), cafe.getName(), distance));
        }
    }

    // 두 좌표 사이의 거리를 구하는 함수
    // dsitance(첫번쨰 좌표의 위도, 첫번째 좌표의 경도, 두번째 좌표의 위도, 두번째 좌표의 경도)
    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1609.344;

        return dist; //단위 meter
    }

    //10진수를 radian(라디안)으로 변환
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
