package com.matnrocha.book_network.book;

import com.matnrocha.book_network.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    public Integer save(BookRequest request, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        //BookRequest -> Book Entity
        Book book = mapper.toBook(request);
        book.setOwner(user);

        return repository.save(book).getId();
    }

    public BookResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("No book found with the Id: " + id));
    }
}
