package kr.ac.cnu.swacademy.cagong.repository;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    @Query("SELECT c FROM Cafe c ORDER BY c.id DESC")
    List<Cafe> findAllDesc();

    List<Cafe> findByNameLike(String keyword);

    List<Cafe> findAllByOrderByStudyScoreDesc();

    List<Cafe> findAllByOrderByAveragePriceAsc();

    List<Cafe> findAllByOrderByAverageScoreDesc();
}
