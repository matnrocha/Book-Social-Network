package com.matnrocha.book_network.book;

import com.matnrocha.book_network.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@ResponseStatus
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {
    private final BookService service;

    @PostMapping
    public ResponseEntity<Integer> saveBook(
            @Valid @RequestBody BookRequest request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<BookResponse> findBookById(
            @PathVariable("book-id") Integer id
    ){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> findAllBooks(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllBooks(page, size, connectedUser));
    }
}
