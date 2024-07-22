package com.matnrocha.book_network.feedback;

import com.matnrocha.book_network.book.Book;
import com.matnrocha.book_network.book.BookRepository;
import com.matnrocha.book_network.common.PageResponse;
import com.matnrocha.book_network.exception.OperationNotPermittedException;
import com.matnrocha.book_network.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedBackRepository feedbackRepository;
    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;

    public Integer save(FeedbackRequest request, Authentication connectedUser) {
        //checks if book exists
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("No book found with ID:: " + request.bookId()));

        if (book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot give a feedback for and archived or not shareable book");
        }

        User user = ((User) connectedUser.getPrincipal());
        if (Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot give feedback to your own book");
        }

        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }

    public PageResponse<FeedbackResponse> findAllFeedbacksByBook(Integer bookId, int page, int size, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        //gets pageable
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Feedback> feedbacks = feedbackRepository.findAllByBookId(bookId , pageable);   //page to display
        List<FeedbackResponse> feedbackResponses = feedbacks.stream()                                   //converting into desired form
                .map(f -> feedbackMapper.toFeedbackResponse(f, user.getId()))
                .toList();

        return new PageResponse<>(
                feedbackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );
    }
}
