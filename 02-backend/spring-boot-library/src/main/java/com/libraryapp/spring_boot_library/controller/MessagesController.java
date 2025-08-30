package com.libraryapp.spring_boot_library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryapp.spring_boot_library.entity.Message;
import com.libraryapp.spring_boot_library.requestmodels.AdminQuestionRequest;
import com.libraryapp.spring_boot_library.service.MessagesService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessagesController {

  private MessagesService messagesService;

  @Autowired
  public MessagesController(MessagesService messagesService) {
    this.messagesService = messagesService;
  }

  @PostMapping("/secure/add/message")
  public void postMessage(@AuthenticationPrincipal Jwt jwt, @RequestBody Message messageRequest) {
    String userEmail = jwt.getClaim("https://luv2code-react-library.com/email");

    messagesService.postMessage(messageRequest, userEmail);
  }

  @PutMapping("/secure/admin/message")
  public void putMessage(
      @AuthenticationPrincipal Jwt jwt, @RequestBody AdminQuestionRequest adminQuestionRequest)
      throws Exception {
    String userEmail = jwt.getClaim("https://luv2code-react-library.com/email");
    System.out.println("User email: " + userEmail);
    List<String> roles = jwt.getClaimAsStringList("https://luv2code-react-library.com/roles");

    String admin = roles != null && !roles.isEmpty() ? roles.get(0) : null;
    System.out.println("User role: " + admin);
    if (admin == null || !admin.equals("admin")) {
      throw new Exception("Administration page only.");
    }
    System.out.println("Admin question request: " + adminQuestionRequest);
    messagesService.putMessage(adminQuestionRequest, userEmail);
  }
}
