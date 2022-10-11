package kr.ac.cnu.swacademy.cagong.repository;

import kr.ac.cnu.swacademy.cagong.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Modifying
    @Query(value="INSERT INTO answer(user_id, question_id, comment) VALUES(?1, ?2, ?3)", nativeQuery = true)
    int mSave(Long userId, Long questionId, String comment);
}
