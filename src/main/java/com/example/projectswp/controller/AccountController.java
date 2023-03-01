package com.example.projectswp.controller;

import com.example.projectswp.model.UserAccount;
import com.example.projectswp.repositories.UserAccountRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/useraccount")
@EnableGlobalMethodSecurity(prePostEnabled = true)
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

    @PostMapping("/create")
    public ResponseEntity<UserAccount> createAccount() {
        boolean result = false;
        return null;
    }
    @PutMapping("")
    public ResponseEntity<UserAccount> updateAccount() {
        return null;
    }

    @GetMapping("")
    public ResponseEntity<UserAccount> getUserAccount() {
        return null;
    }

    @PatchMapping("/ban")
    public ResponseEntity<UserAccount> banAccount() {
        return null;
    }

    @PatchMapping("/unban")
    public ResponseEntity<UserAccount> unbanAccount() {
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<UserAccount> getAccounts() {
        return null;
    }

    @GetMapping("token")
    public ResponseEntity<UserAccount> getAccountToken() {
        return null;
    }


}
