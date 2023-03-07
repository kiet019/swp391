package com.example.projectswp.controller;


import com.example.projectswp.model.Reply;
import com.example.projectswp.repositories.ReplyRepository;
import com.example.projectswp.repositories.UserAccountRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ReplyController {

    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    @PostMapping("Reply/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Object> insertReply(@RequestBody Reply reply) {
        return replyRepository.insertReply(reply, Ultil.getUserId()) ? ResponseEntity.ok().body("success") : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("reply")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateReply(@RequestBody Reply reply) {
        return replyRepository.updateReply(reply, Ultil.getUserId()) ? ResponseEntity.ok().body("success") : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @DeleteMapping("reply/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Object> deleteReply(@RequestBody Reply reply) {
        int userID = Ultil.getUserId();
        if (userAccountRepository.getUserAccountRole(Ultil.getUserCode()) == 1) {
            userID = replyRepository.getUserIDbyReplyID(reply);
        }
        return replyRepository.deleteReply(reply, userID) ? ResponseEntity.ok().body("success") : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
