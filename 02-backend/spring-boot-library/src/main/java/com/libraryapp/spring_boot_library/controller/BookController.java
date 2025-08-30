package com.libraryapp.spring_boot_library.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libraryapp.spring_boot_library.entity.Book;
import com.libraryapp.spring_boot_library.responsemodels.ShelfCurrentLoansResponse;
import com.libraryapp.spring_boot_library.service.BookService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/secure/currentloans")
  public List<ShelfCurrentLoansResponse> currentLoans(@AuthenticationPrincipal Jwt jwt)
      throws Exception {
    String userEmail = jwt.getClaim("https://luv2code-react-library.com/email");
    return bookService.currentLoans(userEmail);
  }

  @GetMapping("/secure/currentloans/count")
  public int currentLoansCount(@AuthenticationPrincipal Jwt jwt) {
    String userEmail = jwt.getClaim("https://luv2code-react-library.com/email");

    System.out.println("User email in controller: " + userEmail);
    return bookService.currentLoansCount(userEmail);
  }

  @GetMapping("/secure/ischeckedout/byuser")
  public Boolean checkoutBookByUser(@AuthenticationPrincipal Jwt jwt, @RequestParam Long bookId) {
    String userEmail = jwt.getClaim("https://luv2code-react-library.com/email");
    return bookService.checkoutBookByUser(userEmail, bookId);
  }

  @PutMapping("/secure/checkout")
  public Book checkoutBook(@AuthenticationPrincipal Jwt jwt, @RequestParam Long bookId)
      throws Exception {
    String userEmail = jwt.getClaim("https://luv2code-react-library.com/email");
    return bookService.checkoutBook(userEmail, bookId);
  }

  @PutMapping("/secure/return")
  public void returnBook(@AuthenticationPrincipal Jwt jwt, @RequestParam Long bookId)
      throws Exception {
    System.out.println("Returning book...");
    String userEmail = jwt.getClaim("https://luv2code-react-library.com/email");
    System.out.println("User email in controller: " + userEmail);
    System.out.println("Book ID in controller: " + bookId);
    bookService.returnBook(userEmail, bookId);
  }

  @PutMapping("/secure/renew/loan")
  public void renewLoan(@AuthenticationPrincipal Jwt jwt, @RequestParam Long bookId)
      throws Exception {
    String userEmail = jwt.getClaim("https://luv2code-react-library.com/email");

    System.out.println("User email in controller: " + userEmail);
    System.out.println("Book ID in controller: " + bookId);
    bookService.renewLoan(userEmail, bookId);
  }
}
