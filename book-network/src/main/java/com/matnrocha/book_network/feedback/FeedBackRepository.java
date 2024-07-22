package com.matnrocha.book_network.feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedBackRepository extends JpaRepository<Feedback, Integer> {
    @Query("""
            SELECT feedbacks
            FROM Feedback feedbacks
            WHERE feedbacks.book.id = :bookId
    """)
    Page<Feedback> findAllByBookId(@Param("bookId") Integer bookId, Pageable pageable);
}
