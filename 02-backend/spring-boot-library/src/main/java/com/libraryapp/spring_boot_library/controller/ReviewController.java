package com.libraryapp.spring_boot_library.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libraryapp.spring_boot_library.requestmodels.ReviewRequest;
import com.libraryapp.spring_boot_library.service.ReviewService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

  private ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/secure/user/book")
  public Boolean reviewBookByUser(@AuthenticationPrincipal Jwt jwt, @RequestParam Long bookId)
      throws Exception {
    String userEmail = jwt.getClaim("https://luv2code-react-library.com/email");
    System.out.println("User email from JWT: " + userEmail); // Debugging line

    if (userEmail == null) {
      throw new Exception("User email is missing");
    }
    return reviewService.userReviewListed(userEmail, bookId);
  }

  @PostMapping("/secure")
  public void postReview(@AuthenticationPrincipal Jwt jwt, @RequestBody ReviewRequest reviewRequest)
      throws Exception {
    String userEmail = jwt.getClaim("https://luv2code-react-library.com/email");
    System.out.println("User email from JWT: " + userEmail); // Debugging line

    if (userEmail == null) {
      throw new Exception("User email is missing");
    }
    reviewService.postReview(userEmail, reviewRequest);
  }
}
