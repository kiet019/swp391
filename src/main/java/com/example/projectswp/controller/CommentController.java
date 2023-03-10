package com.example.projectswp.controller;


import com.example.projectswp.data_view_model.blogcategory.ReturnMessage;
import com.example.projectswp.data_view_model.comment.CommentCreateVM;
import com.example.projectswp.data_view_model.comment.CommentGetVM;
import com.example.projectswp.data_view_model.comment.CommentIdVM;
import com.example.projectswp.data_view_model.comment.CommentUpdateVM;
import com.example.projectswp.model.Comment;
import com.example.projectswp.repositories.CommentRepository;
import com.example.projectswp.repositories.ultil.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CommentController {
    @Autowired
    CommentRepository commentRepository;
    @GetMapping("/all")
    public ResponseEntity<Object> getComments() {
        try{

            List<Comment> commentList = commentRepository.getComments();
            return commentList != null ? ResponseEntity.ok(commentList) : ResponseEntity.notFound().build();

        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(ReturnMessage.create(ex.getMessage()));
        }
    }
    @GetMapping("")
    public ResponseEntity<Object> getComment(@ModelAttribute CommentGetVM commentGetVM) {
        try{

            if(commentGetVM.getItemId() != 0){
                List<Comment> commentList = commentRepository.getCommentByItemId(commentGetVM.getItemId());
                return commentList != null ? ResponseEntity.ok(commentList) : ResponseEntity.notFound().build();
            }else if(commentGetVM.getBlogId() != 0) {
                List<Comment> commentList = commentRepository.getCommentByBlogId(commentGetVM.getBlogId());
                return commentList != null ? ResponseEntity.ok(commentList) : ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().body(ReturnMessage.create("no BlogID either ItemID"));

        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(ReturnMessage.create(ex.getMessage()));
        }
    }
    @PutMapping("")
    public ResponseEntity<Object> updateComment(@RequestBody CommentUpdateVM commentUpdateVM) {
        try{

            int uid = Ultil.getUserId();
            boolean result = commentRepository.updateComment(uid, commentUpdateVM);
            return result? ResponseEntity.ok().body(ReturnMessage.create("success")) : ResponseEntity.badRequest().body(ReturnMessage.create("fail to update"));

        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(ReturnMessage.create(ex.getMessage()));
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createComment(@RequestBody CommentCreateVM createVM) {
        try{

            int uid = Ultil.getUserId();
            boolean result = commentRepository.createComment(uid, createVM);
            return result? ResponseEntity.ok().body(ReturnMessage.create("success")) : ResponseEntity.badRequest().body(ReturnMessage.create("request fail"));

        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(ReturnMessage.create(ex.getMessage()));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteComment(@RequestBody CommentIdVM cmtId) {
        try{

            int uid = Ultil.getUserId();
            boolean result = commentRepository.deleteComment(uid, cmtId.getCommentId());
            return result? ResponseEntity.ok().body(ReturnMessage.create("success")) : ResponseEntity.badRequest().body(ReturnMessage.create("Error at delete Comment"));

        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(ReturnMessage.create(ex.getMessage()));
        }
    }
}
