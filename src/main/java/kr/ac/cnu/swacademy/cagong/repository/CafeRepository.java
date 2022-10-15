package kr.ac.cnu.swacademy.cagong.repository;

import kr.ac.cnu.swacademy.cagong.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {

    Optional<Cafe> findByName(String name);

    @Query("SELECT c FROM Cafe c ORDER BY c.id DESC")
    List<Cafe> findAllDesc();
}
