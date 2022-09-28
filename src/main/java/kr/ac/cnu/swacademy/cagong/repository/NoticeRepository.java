package kr.ac.cnu.swacademy.cagong.repository;

import kr.ac.cnu.swacademy.cagong.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT n FROM Notice n ORDER BY n.id DESC")
    List<Notice> findAllDesc();
}
