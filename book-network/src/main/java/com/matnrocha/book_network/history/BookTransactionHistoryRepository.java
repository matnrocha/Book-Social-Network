package com.matnrocha.book_network.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {

    @Query("""
        SELECT transaction
        FROM BookTransactionHistory transaction
        WHERE transaction.book.id = :bookId
        AND transaction.book.owner.id = :userId
        AND transaction.returned = true
        AND transaction.returnApproved = false
    """)
    Optional<BookTransactionHistory> findByBookIdAndOwnerId(@Param("bookId") Integer bookId, @Param("userId") Integer userId);

    @Query("""
              SELECT history
              FROM BookTransactionHistory history
              WHERE history.user.id = :userId
    """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, @Param("userId") Integer userId);

    @Query("""
              SELECT history
              FROM BookTransactionHistory history
              WHERE history.book.owner.id = :userId
    """)
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, @Param("userId") Integer userId);

    @Query("""
            SELECT 
            (COUNT(*) > 0) as isBorrowed
            FROM BookTransactionHistory BookTransactionHistory
            WHERE bookTransactionHistory.user.id = :userId
            AND bookTransactionHistory.book.id = :bookId
            AND bookTransactionHistory.returnApproved = false
    """)
    boolean isAlreadyBorrowedByUser(@Param("bookId")Integer bookId, @Param("userId")Integer userId);

    @Query("""
            SELECT
            (COUNT (*) > 0) AS isBorrowed
            FROM BookTransactionHistory bookTransactionHistory
            WHERE bookTransactionHistory.book.id = :bookId
            AND bookTransactionHistory.returnApproved = false
            """)
    boolean isAlreadyBorrowed(@Param("bookId") Integer bookId);

    @Query("""
            SELECT transaction
            FROM BookTransactionHistory transaction
            WHERE transaction.book.id = :bookId
            AND transaction.user.id = :userId
            AND transaction.returned = false
            AND transaction.returnApproved = false
    """)
    Optional<BookTransactionHistory> findByBookIdAndUserId(@Param("bookId") Integer bookId, @Param("userId") Integer userId);
}
