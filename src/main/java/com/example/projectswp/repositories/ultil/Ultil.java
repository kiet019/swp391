package com.example.projectswp.repositories.ultil;

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
        int userId = (int) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return userId;
    }
}
