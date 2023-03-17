package com.example.projectswp.repositories.ultil;

import com.example.projectswp.model.UserAccount;
import com.example.projectswp.service.Gmail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Ultil {
    public static Date getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return new Date(dtf.format(now));
    }

    public static int getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = Integer.parseInt(authentication.getCredentials().toString());
        return userId;
    }
    public static String getUserCode() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userCode= authentication.getPrincipal().toString();
        return userCode;
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
     public static void sendMail(String subject, String message, String email) throws GeneralSecurityException, IOException, MessagingException {
         Gmail gmail = new Gmail();
         gmail.sendMessage(subject, message, email);
     }
}
