package kr.ac.cnu.swacademy.cagong.repository;

import kr.ac.cnu.swacademy.cagong.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT p FROM Question p ORDER BY p.id DESC")
    Page<Question> findAllDesc(Pageable pageable);

}
