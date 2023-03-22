package com.example.projectswp.controller;

import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
import com.example.projectswp.data_view_model.user.UpdateUserVM;
import com.example.projectswp.data_view_model.user.UserIdVM;
import com.example.projectswp.model.UserAccount;
import com.example.projectswp.repositories.UserAccountRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AccountController {
    @Autowired
    UserAccountRepository userAccountRepository;
    @PostMapping ("/login")
    public ResponseEntity<String> getUserAccount(HttpServletRequest request) throws FirebaseAuthException {
        try {
            //lay token
            String code = request.getHeader("Authorization");
//            System.out.println(code);
//            //tim tren firebase token
//            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(code);
//            //lay uid
//            String uid = decodedToken.getUid();
//            //lay thong tin trong db
//            System.out.println(uid);
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
            boolean result = false;
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(Ultil.getUserCode());
            if (Ultil.getUserId() == 0)
                result = userAccountRepository.addUserAccount(userAccount, userRecord, 50);
//            Ultil.sendMail("Create Accounnt", "Create account success, welcome to MOBY shop", userRecord.getEmail());
            return result ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateAccount(@RequestBody UpdateUserVM updateUserVM) {
        try {
            int uid = Ultil.getUserId();
            boolean result = userAccountRepository.updateUser(uid, updateUserVM);
            return result ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserAccount> getUserAccount(@RequestParam int UserId) {
        try {
            UserAccount userAccount = userAccountRepository.getUserAccountById(UserId);
            return userAccount != null ? ResponseEntity.ok(userAccount) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/ban")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> banAccount(@RequestBody UserIdVM userIdVM) {
        try {
            boolean result = userAccountRepository.updateUserStatus(userIdVM.getUserId(), false);
            return result ? ResponseEntity.ok(ReturnMessage.create("ban success")) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return  ResponseEntity.badRequest().build();
        }

    }

    @PatchMapping("/unban")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> unbanAccount(@RequestBody UserIdVM userIdVM) {
        try {
            boolean result = userAccountRepository.updateUserStatus(userIdVM.getUserId(), true);
            return result ? ResponseEntity.ok(ReturnMessage.create("unban success")) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return  ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserAccount>> getAccounts( @RequestParam int pageNumber, @RequestParam int pageSize) {
        try {
            int skip = (pageNumber-1)*5;
            int getNumber = pageSize;
            List<UserAccount> list = userAccountRepository.getUserAccounts(skip, getNumber);
            return list != null ? ResponseEntity.ok(list) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserAccount> getAccountToken() {
        int uid = Ultil.getUserId();
        UserAccount userAccount = userAccountRepository.getUserAccountById(uid);
        return userAccount != null ? ResponseEntity.ok(userAccount) : ResponseEntity.notFound().build();
    }


}
