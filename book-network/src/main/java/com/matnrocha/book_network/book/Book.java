package com.matnrocha.book_network.book;

import com.matnrocha.book_network.common.BaseEntity;
import com.matnrocha.book_network.feedback.Feedback;
import com.matnrocha.book_network.history.BookTransactionHistory;
import com.matnrocha.book_network.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntity {

    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;       //file path of the uploaded cover
    private boolean archived;
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;



    @Transient
    public double getRate(){
        if(feedbacks == null || feedbacks.isEmpty()) {
            return 0.0;
        }

        var avgRate = feedbacks.stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);
        return (double) Math.round(avgRate);    //double rounded = Math.round(avgRate * 10.0) / 10.0
    }



}
