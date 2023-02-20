package com.example.projectswp.controller;

import com.example.projectswp.model.UserAccount;
import com.example.projectswp.repositories.UserAccountRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
public class AccountController {
    @Autowired
    UserAccountRepository userAccountRepository;

    @PostMapping ("/login")
    public ResponseEntity<UserAccount> getUserAccount(HttpServletRequest request) throws FirebaseAuthException {
        String userUid = request.getHeader("Authorization");
        UserRecord userRecord = FirebaseAuth.getInstance().getUser(userUid);
        UserAccount userAccount = userAccountRepository.getUserAccount(userRecord.getUid());
        if (userAccount == null) {
            userAccountRepository.addUserAccount(userRecord);
            userAccount = userAccountRepository.getUserAccount(userRecord.getUid());
        }
        return ResponseEntity.ok(userAccount);
    }

    @PostMapping("/api/hello")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String code = (String) authentication.getPrincipal();
        return ResponseEntity.ok(code);
    }

}
