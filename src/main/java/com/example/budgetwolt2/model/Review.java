package com.example.budgetwolt2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rating;
    private String reviewText;
    private LocalDate dateCreated;
    @ManyToOne
    private BasicUser commentOwner;
    @ManyToOne
    private BasicUser feedBack;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BasicUser getCommentOwner() {
        return commentOwner;
    }

    public void setCommentOwner(BasicUser commentOwner) {
        this.commentOwner = commentOwner;
    }

    public BasicUser getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(BasicUser feedBack) {
        this.feedBack = feedBack;
    }
}
