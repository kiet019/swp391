package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
import com.example.projectswp.data_view_model.user.UpdateUserVM;
import com.example.projectswp.data_view_model.user.UserIdVM;
import com.example.projectswp.model.UserAccount;
import com.example.projectswp.repositories.UserAccountRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import com.example.projectswp.service.Gmail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
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
import java.util.List;

@RestController
@RequestMapping("/api/useraccount")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AccountController {
    @Autowired
    UserAccountRepository userAccountRepository;
    @PostMapping ("/login")
    public ResponseEntity<String> getUserAccount(HttpServletRequest request) throws FirebaseAuthException {
        try {
            //lay token
            String code = request.getHeader("Authorization");
            //tim tren firebase token
//            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(code);
//            //lay uid
//            String uid = decodedToken.getUid();
            //lay thong tin trong db
            System.out.println(code);
            UserAccount userAccount = userAccountRepository.getUserAccount(code);
            URI uri = URI.create("");
            if (userAccount != null) {
                return ResponseEntity.created(uri).body("User Exist");
            } else {
                return ResponseEntity.created(uri).body("New User");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserAccount userAccount) throws FirebaseAuthException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(authentication.getPrincipal().toString());
            boolean result = userAccountRepository.addUserAccount(userAccount, userRecord);
            Gmail.sendMessage("Create Account", "Welcome to Moby shop", userRecord.getEmail());
            return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ReturnMessage.create(e.getMessage()));
        }
    }

    @PutMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Object> updateAccount(@RequestBody UpdateUserVM updateUserVM) {
        int uid = Ultil.getUserId();
        boolean result = userAccountRepository.updateUser(uid, updateUserVM);
        return result ? ResponseEntity.ok(ReturnMessage.create("update success")) : ResponseEntity.notFound().build();
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserAccount> getUserAccount(@RequestParam int UserId) {
        UserAccount userAccount = userAccountRepository.getUserAccountById(UserId);
        return userAccount != null ? ResponseEntity.ok(userAccount) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/ban")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> banAccount(@RequestBody UserIdVM userIdVM) {
        boolean result = userAccountRepository.updateUserStatus(userIdVM.getUserId(), false);
        return result ? ResponseEntity.ok(ReturnMessage.create("ban success")) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/unban")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> unbanAccount(@RequestBody UserIdVM userIdVM) {
        boolean result = userAccountRepository.updateUserStatus(userIdVM.getUserId(), true);
        return result ? ResponseEntity.ok(ReturnMessage.create("unban success")) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserAccount>> getAccounts() {
        List<UserAccount> list = userAccountRepository.getUserAccounts();
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.notFound().build();
    }

    @GetMapping("/token")
    public ResponseEntity<UserAccount> getAccountToken() {
        int uid = Ultil.getUserId();
        UserAccount userAccount = userAccountRepository.getUserAccountById(uid);
        return userAccount != null ? ResponseEntity.ok(userAccount) : ResponseEntity.notFound().build();
    }


}
