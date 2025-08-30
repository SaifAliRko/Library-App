package com.libraryapp.spring_boot_library.service;

import com.libraryapp.spring_boot_library.dao.ReviewRepository;
import com.libraryapp.spring_boot_library.entity.Review;
import com.libraryapp.spring_boot_library.requestmodels.ReviewRequest;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {

  private ReviewRepository reviewRepository;

  @Autowired
  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
    Review validateReview =
        reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());
    if (validateReview != null) {
      throw new Exception("Review already created");
    }

    Review review = new Review();
    review.setBookId(reviewRequest.getBookId());
    review.setRating((int) reviewRequest.getRating());
    review.setUserEmail(userEmail);
    if (reviewRequest.getReviewDescription().isPresent()) {
      review.setReviewDescription(
          reviewRequest.getReviewDescription().map(Object::toString).orElse(null));
    }
    review.setDate(LocalDate.now().toString());
    reviewRepository.save(review);
  }

  public Boolean userReviewListed(String userEmail, Long bookId) {
    Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
    return (validateReview != null);
  }
}
