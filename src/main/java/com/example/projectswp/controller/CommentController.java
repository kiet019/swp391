package com.example.projectswp.controller;


import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CommentController {
}
