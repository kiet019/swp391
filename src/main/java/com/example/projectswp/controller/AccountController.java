package com.example.projectswp.controller;

import com.example.projectswp.model.UserAccount;
import com.example.projectswp.repositories.UserAccountRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
public class AccountController {
    @Autowired
    UserAccountRepository userAccountRepository;

    @PostMapping ("/login")
    public ResponseEntity<UserAccount> getUserAccount(HttpServletRequest request) {
        UserRecord userRecord = (UserRecord) request.getAttribute("userRecord");
        UserAccount userAccount = userAccountRepository.getUserAccount(userRecord.getUid());
        if (userAccount == null) {
            userAccountRepository.addUserAccount(userRecord);
            userAccount = userAccountRepository.getUserAccount(userRecord.getUid());
        }
        return ResponseEntity.ok(userAccount);
    }


}
