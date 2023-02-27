package com.example.projectswp.controller;

import com.example.projectswp.model.UserAccount;
import com.example.projectswp.repositories.UserAccountRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("api/useraccount")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AccountController {
    @Autowired
    UserAccountRepository userAccountRepository;

    @PostMapping ("login")
    public ResponseEntity<UserAccount> getUserAccount(HttpServletRequest request) throws FirebaseAuthException {
        try {
            //lay token
            String code = request.getHeader("Authorization");
            //tim tren firebase token
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(code);
            //lay uid
            String uid = decodedToken.getUid();
            //lay thong tin trong db
            UserAccount userAccount = userAccountRepository.getUserAccount(uid);
            if (userAccount != null) {
                URI uri = new URI("User Existed");
                return ResponseEntity.created(uri).build();
            } else {
                URI uri = new URI("New User");
                return ResponseEntity.created(uri).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("create")
    public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount userAccount) throws FirebaseAuthException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(authentication.getPrincipal().toString());
            boolean result = userAccountRepository.addUserAccount(userAccount, userRecord);
            return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
