package com.example.projectswp.repositories.ultil;

import com.example.projectswp.model.UserAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Ultil {
    public static Date getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return new Date(dtf.format(now));
    }

    public static int getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (int)authentication.getPrincipal();
    }
    public static String getUserCode() {
        return null;
    }
}
